package com.jobseeker.hrms.candidate.repository.candidate.explore;

import com.jobseeker.hrms.candidate.config.GlobalVariable;
import com.jobseeker.hrms.candidate.data.basic.request.explore.ExploreDataRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.jobseeker.candidate.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
@SuppressWarnings("SpringQualifierCopyableLombok")
public class IExploreRepositoryImpl implements IExploreRepositoryExtend {

    @Autowired
    @Qualifier("candidateMongoTemplate")
    private MongoTemplate mongoTemplate;

    @Value("${atlas.search-xplore-index}")
    private String searchXploreIDX;

    private final GlobalVariable globalVariable;

    @Override
    public Page<Candidate> findCandidates(ExploreDataRequest exploreDataRequest) {
        return doQuery(exploreDataRequest, Candidate.class);
    }

    private <T> Page<T> doQuery(ExploreDataRequest params, Class<T> entity) {
        try {
            int page = params.getPage() - 1;
            Pageable pageable = PageRequest.of(page, params.getLimit(), params.getSortDirection(), params.getSortedField().getDatabaseFieldName());

            List<Document> mustPipeline = new ArrayList<>();
            List<Document> shouldPipeline = new ArrayList<>();

            globalVariable.setLon(Double.parseDouble(params.getLng()));
            globalVariable.setLat(Double.parseDouble(params.getLat()));

            addPipelineIfPresent(mustPipeline, params.getIsVideo(), new Function<Object, Document>() {
                @Override
                public Document apply(Object o) {
                    return new Document("regex",
                            new Document("path", "video_resume.video_resume").append("query", ".{1,}"));
                }
            });

            addPipelineIfPresent(mustPipeline, params.getKeyword(), keyword ->
                    new Document("compound", new Document("should", List.of(
                            createAutocompleteDocument("job_function.name.id", keyword.toString()),
                            createAutocompleteDocument("job_function.name.en", keyword.toString()),
                            createAutocompleteDocument("name", keyword.toString()),
                            createAutocompleteDocument("position", keyword.toString())
                    )).append("minimumShouldMatch", 1))
            );

            if (!params.getJobFuncIdsBasedOnCategory().isEmpty()) {
                mustPipeline.add(new Document("in", new Document("path", "job_function._id")
                        .append("value", params.getJobFuncIdsBasedOnCategory().stream().map(ObjectId::new).toList())
                        .append("score", new Document("boost", new Document("value", 4L)))));
            }

            addPipelineIfPresent(mustPipeline, params.getProvinceId(), id ->
                    new Document("equals", new Document("path", "province._id").append("value", new ObjectId(id.toString()))));

            addPipelineIfPresent(mustPipeline, params.getCityId(), id ->
                    new Document("equals", new Document("path", "city._id").append("value", new ObjectId(id.toString()))));

            addPipelineIfPresent(mustPipeline, params.getEducationId(), id ->
                    new Document("equals", new Document("path", "last_education.degree._id").append("value", new ObjectId(id.toString()))));

            addPipelineIfPresent(mustPipeline, params.getGenderId(), gender ->
                    new Document("text", new Document("path", "gender.type").append("query", gender)));

            shouldPipeline.add(new Document("near", new Document("origin", new Document("type", "Point")
                    .append("coordinates", List.of(globalVariable.getLon(), globalVariable.getLat())))
                    .append("pivot", 1000L).append("path", "coordinate")
                    .append("score", new Document("boost", new Document("value", 3L)))));

            if (!params.getJobFunctionIds().isEmpty() && params.getIsRecommendation()) {
                shouldPipeline.add(new Document("in", new Document("path", "job_function._id")
                        .append("value", params.getJobFunctionIds().stream().map(ObjectId::new).toList())
                        .append("score", new Document("boost", new Document("value", 4L)))));
            }

            // Build and run the aggregation pipeline
            Aggregation pipeline = createAggregationPipeline(mustPipeline, shouldPipeline, params, pageable);
            return executePipeline(pipeline, entity, pageable);

        } catch (Exception ex) {
            return null;
        }
    }

    private void addPipelineIfPresent(List<Document> pipeline, Object param, Function<Object, Document> documentBuilder) {
        if (param instanceof Boolean) {
            if ((Boolean) param) {
                Optional.ofNullable(param).ifPresent(value -> pipeline.add(documentBuilder.apply(value)));
            }
        } else {
            Optional.ofNullable(param).ifPresent(value -> pipeline.add(documentBuilder.apply(value)));
        }
    }

    private Document createAutocompleteDocument(String path, String query) {
        return new Document("autocomplete", new Document("path", path)
                .append("query", query).append("score", new Document("boost", new Document("value", 1))));
    }

    private <T> Page<T> executePipeline(Aggregation pipeline, Class<T> entity, Pageable pageable) {
        AggregationResults<Document> rawResults = mongoTemplate.aggregate(pipeline, "candidates", Document.class);
        Optional<Document> firstResult = rawResults.getMappedResults().stream().findFirst();
        long totalCount = firstResult.map(doc -> doc.getLong("count")).orElse(0L);
        List<T> mappedResults = rawResults.getMappedResults().stream()
                .map(doc -> mongoTemplate.getConverter().read(entity, doc))
                .collect(Collectors.toList());

        return new PageImpl<>(mappedResults, pageable, totalCount);
    }

    private Aggregation createAggregationPipeline(List<Document> mustPipeline, List<Document> shouldPipeline, ExploreDataRequest params, Pageable pageable) {
        return Aggregation.newAggregation(
                a -> new Document("$search", new Document("index", searchXploreIDX)
                        .append("compound", new Document("must", mustPipeline).append("should", shouldPipeline))
                        .append("scoreDetails", true)
                        .append("sort", new Document("unused", new Document("$meta", "searchScore").append("order", -1L)))),
                (AggregationOperation) context -> new Document("$addFields", new Document("count", "$$SEARCH_META.count.lowerBound")),
                Aggregation.skip((long) (params.getPage() - 1) * params.getLimit()),
                Aggregation.limit(params.getLimit())
        );
    }

}



