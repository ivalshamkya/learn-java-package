package com.jobseeker.hrms.candidate.repository.candidate;

import com.jobseeker.hrms.candidate.config.ServletRequestAWS;
import com.jobseeker.hrms.candidate.data.basic.request.CandidateSearchExcelRequest;
import com.jobseeker.hrms.candidate.data.basic.request.CandidateSearchParamRequest;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.jobseeker.helper.ObjectMapperHelper;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BaseRepositoryImpl {

	@Autowired
	@Qualifier("candidateMongoTemplate")
	private MongoTemplate mongoTemplate;

	private final ServletRequestAWS servletRequestAWS;

	@Value("${atlas.search-candidate-index}")
	private String searchCandidateIDX;

	public <T> List<T> findAll(Object params, Class<T> entityClass) {
		final List<Document> mustPipeLine = new ArrayList<>();
		ObjectId companyId = new ObjectId(ServletRequestAWS.getCompanyId());

		CandidateSearchParamRequest param = ObjectMapperHelper.Convert(params, CandidateSearchParamRequest.class);

		//<editor-fold desc="Query">
		Document filterFromCompany = new Document("equals",
				new Document("value", companyId)
						.append("path", "from_employer._id"));

		mustPipeLine.add(filterFromCompany);

		if (param.getProvince() != null) {
			Document filterProvince =
					new Document("equals",
							new Document("value", param.getProvince())
									.append("path", "province._id"));
			mustPipeLine.add(filterProvince);
		}

		if (param.getEducation() != null) {
			Document filterLastEducation =
					new Document("equals",
							new Document("value", param.getEducation())
									.append("path", "last_education.degree._id"));
			mustPipeLine.add(filterLastEducation);
		}

		if (param.getVacancyId() != null) {
			Document filterPosition =
					new Document("equals",
							new Document("value", param.getVacancyId())
									.append("path", "last_position._id"));
			mustPipeLine.add(filterPosition);
		}

		if (param.getQ() != null && !param.getQ().isEmpty()) {
			Document keyword = new Document("compound",
					new Document("should", Arrays.asList(
							new Document("autocomplete",
									new Document("query", param.getQ())
											.append("path", "name")),
							new Document("autocomplete",
									new Document("query", param.getQ())
											.append("path", "email")),
							new Document("autocomplete",
									new Document("query", param.getQ())
											.append("path", "province.name")),
							new Document("autocomplete",
									new Document("query", param.getQ())
											.append("path", "last_position.name")),
							new Document("autocomplete",
									new Document("query", param.getQ())
											.append("path", "city.name"))))
							.append("minimumShouldMatch", 1L));
			mustPipeLine.add(keyword);
		}

		boolean sortField;
		Document sortDoc;
		if ((param.getSortedField() != null)
				&& (param.getSortDirection() != null)) {
			String sortBy = param.getSortedField().getDatabaseFieldName();
			sortDoc = new Document(sortBy != null ? sortBy : "name",
					param.getSortDirection().isAscending() ? 1L : -1L);
			sortField = true;
		} else {
			sortField = false;
			sortDoc = null;
		}

		Aggregation pipeline = Aggregation.newAggregation(
				a -> new Document("$search",
						new Document("index", searchCandidateIDX)
								.append("compound",
										new Document("must", mustPipeLine))
								.append("sort",
										new Document("created_at", -1L))
								.append("sort",
										sortField ? sortDoc : new Document("created_at", -1L))
				),
				(AggregationOperation) context -> new Document("$addFields",
						new Document("count", "$$SEARCH_META.count.lowerBound")),
				Aggregation.skip((long) (param.getPage() - 1) * param.getLimit()),
				Aggregation.limit(param.getLimit())
		);
		//</editor-fold>
		AggregationResults<T> results = mongoTemplate.aggregate(pipeline, mongoTemplate.getCollectionName(entityClass), entityClass);

		return results.getMappedResults();
	}

	public <T> Page<T> findAllPaginated(Object params, List<String> blacklistedIds, Class<T> entityClass) {
		final List<Document> mustPipeLine = new ArrayList<>();
		final List<Document> mustNotPipeLine = new ArrayList<>();

		ObjectId companyId = new ObjectId(ServletRequestAWS.getCompanyId());
		CandidateSearchParamRequest param = ObjectMapperHelper.Convert(params, CandidateSearchParamRequest.class);

		//<editor-fold desc="Query">
		Document filterFromCompany = new Document("equals",
				new Document("value", companyId)
						.append("path", "from_employer._id"));
		mustPipeLine.add(filterFromCompany);

		if (!blacklistedIds.isEmpty()) {
			List<ObjectId> blacklistedObjectIds = blacklistedIds.stream()
					.map(ObjectId::new)
					.collect(Collectors.toList());

			Document filterBlacklisted = new Document("in",
					new Document("value", blacklistedObjectIds)
							.append("path", "_id"));
			mustNotPipeLine.add(filterBlacklisted);
		}


		if (param.getProvince() != null) {
			Document filterProvince =
					new Document("equals",
							new Document("value", param.getProvince())
									.append("path", "province._id"));
			mustPipeLine.add(filterProvince);
		}

		if (param.getEducation() != null) {
			Document filterLastEducation =
					new Document("equals",
							new Document("value", param.getEducation())
									.append("path", "last_education.degree._id"));
			mustPipeLine.add(filterLastEducation);
		}

		if (param.getVacancyId() != null) {
			Document filterPosition =
					new Document("equals",
							new Document("value", param.getVacancyId())
									.append("path", "last_position._id"));
			mustPipeLine.add(filterPosition);
		}

		if (param.getQ() != null && !param.getQ().isEmpty()) {
			Document keyword = new Document("compound",
					new Document("should", Arrays.asList(
							new Document("autocomplete",
									new Document("query", param.getQ())
											.append("path", "name")),
							new Document("autocomplete",
									new Document("query", param.getQ())
											.append("path", "email")),
							new Document("autocomplete",
									new Document("query", param.getQ())
											.append("path", "province.name")),
							new Document("autocomplete",
									new Document("query", param.getQ())
											.append("path", "last_position.name")),
							new Document("autocomplete",
									new Document("query", param.getQ())
											.append("path", "city.name"))))
							.append("minimumShouldMatch", 1L));
			mustPipeLine.add(keyword);
		}

		boolean sortField;
		Document sortDoc;
		if ((param.getSortedField() != null)
				&& (param.getSortDirection() != null)) {
			String sortBy = param.getSortedField().getDatabaseFieldName();
			sortDoc = new Document(sortBy != null ? sortBy : "name",
					param.getSortDirection().isAscending() ? 1L : -1L);
			sortField = true;
		} else {
			sortField = false;
			sortDoc = null;
		}

		Aggregation pipeline = Aggregation.newAggregation(
				a -> new Document("$search",
						new Document("index", searchCandidateIDX)
								.append("compound",
										new Document("must", mustPipeLine)
												.append("mustNot", mustNotPipeLine)
								)
								.append("sort",
										new Document("created_at", -1L))
								.append("sort",
										sortField ? sortDoc : new Document("created_at", -1L))
				),
				(AggregationOperation) context -> new Document("$addFields",
						new Document("count", "$$SEARCH_META.count.lowerBound")),
				Aggregation.skip((long) (param.getPage() - 1) * param.getLimit()),
				Aggregation.limit(param.getLimit())
		);
		//</editor-fold>

		// Initialize pagination result
        int page = param.getPage() - 1;
        Pageable pageable = PageRequest.of(
                page, param.getLimit(),
                param.getSortDirection(), param.getSortedField().getDatabaseFieldName()
        );

		// Run aggregate search
		AggregationResults<Document> rawResults = mongoTemplate.aggregate(pipeline, mongoTemplate.getCollectionName(entityClass), Document.class);

		// Extract the count from the first result, if available
		Optional<Document> firstResult = rawResults.getMappedResults().stream().findFirst();
		long totalCount = firstResult
				.map(doc -> doc.getLong("count"))
				.orElse(0L);

		// Convert raw `Document` objects back into the entity class
		List<T> mappedResults = rawResults.getMappedResults().stream()
				.map(doc -> mongoTemplate.getConverter().read(entityClass, doc))
				.collect(Collectors.toList());

		return new PageImpl<>(mappedResults, pageable, totalCount);
	}

	public <T> List<T> findAllExcel(Object params, Class<T> entityClass) {
		final List<Document> mustPipeLine = new ArrayList<>();
		ObjectId companyId = new ObjectId(ServletRequestAWS.getCompanyId());

		CandidateSearchExcelRequest param = ObjectMapperHelper.Convert(params, CandidateSearchExcelRequest.class);

		Document filterFromCompany = new Document("equals",
				new Document("value", companyId)
						.append("path", "from_employer._id"));

		mustPipeLine.add(filterFromCompany);

		if (param.getProvince() != null) {
			Document filterProvince =
					new Document("equals",
							new Document("value", param.getProvince())
									.append("path", "province._id"));
			mustPipeLine.add(filterProvince);
		}

		if (param.getEducation() != null) {
			Document filterLastEducation =
					new Document("equals",
							new Document("value", param.getEducation())
									.append("path", "last_education.degree._id"));
			mustPipeLine.add(filterLastEducation);
		}

		if (param.getVacancyId() != null) {
			Document filterPosition =
					new Document("equals",
							new Document("value", param.getVacancyId())
									.append("path", "last_position._id"));
			mustPipeLine.add(filterPosition);
		}

		if (param.getQ() != null && !param.getQ().isEmpty()) {
			Document keyword = new Document("compound",
					new Document("should", Arrays.asList(
							new Document("autocomplete",
									new Document("query", param.getQ())
											.append("path", "name")),
							new Document("autocomplete",
									new Document("query", param.getQ())
											.append("path", "email")),
							new Document("autocomplete",
									new Document("query", param.getQ())
											.append("path", "province.name")),
							new Document("autocomplete",
									new Document("query", param.getQ())
											.append("path", "last_position.name")),
							new Document("autocomplete",
									new Document("query", param.getQ())
											.append("path", "city.name"))))
							.append("minimumShouldMatch", 1L));
			mustPipeLine.add(keyword);
		}

		Aggregation pipeline = Aggregation.newAggregation(
				a -> new Document("$search",
						new Document("index", searchCandidateIDX)
								.append("compound",
										new Document("must", mustPipeLine))
								.append("sort",
										new Document("updated_at", -1L))
				)
		);

		AggregationResults<T> results = mongoTemplate.aggregate(pipeline,
				mongoTemplate.getCollectionName(entityClass), entityClass);

		return results.getMappedResults();
	}
}
