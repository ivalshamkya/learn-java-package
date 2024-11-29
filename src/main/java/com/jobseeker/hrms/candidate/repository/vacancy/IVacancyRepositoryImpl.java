package com.jobseeker.hrms.candidate.repository.vacancy;

import com.jobseeker.hrms.candidate.config.ServletRequestAWS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
@SuppressWarnings("SpringQualifierCopyableLombok")
public class IVacancyRepositoryImpl implements IVacancyRepositoryExtend {

    @Autowired
    @Qualifier("vacancyMongoTemplate")
    private MongoTemplate mongoTemplate;

    @Value("${atlas.search-vacancy-index}")
    private String searchVacancyIDX;

    @Override
    public List<String> findVacanciesExistsJobFunction() {
        try {
            // Initialize pipeline query
            List<Document> filterPipeline = new ArrayList<>();
            List<Document> mustPipeline = new ArrayList<>();

            Document filterDocument = new Document("equals",
                    new Document("value",
                            new ObjectId(ServletRequestAWS.getCompanyId()))
                            .append("path", "company._id")
            );
            filterPipeline.add(filterDocument);

            Document existDocument = new Document("exists",
                    new Document("path", "job_function._id")
            );
            mustPipeline.add(existDocument);

            // Pipeline
            Aggregation pipeline = Aggregation.newAggregation(
                    a->new Document("$search",
                            new Document("index", searchVacancyIDX)
                                    .append("compound",
                                            new Document("filter", filterPipeline)
                                                    .append("must", mustPipeline)
                                    )
                    ),
                    Aggregation.project().and("job_function._id").as("jobFunctionId")
            );

            // Run aggregate search
            AggregationResults<Document> rawResults = mongoTemplate.aggregate(pipeline, "vacancies", Document.class);

            // Convert raw `Document` objects into List<String>
            return rawResults.getMappedResults().stream()
                    .map(doc -> doc.get("jobFunctionId").toString())
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            return null;
        }
    }
}
