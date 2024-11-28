package com.jobseeker.hrms.organization.repositories.lws.organization.workplacement;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.lawson.WorkplacementLawson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
@Component
@RequiredArgsConstructor
public class WorkplacementOrgLWSRepositoryImpl implements WorkplacementOrgLWSRepositoryExtend {
	@Autowired
	@Qualifier("organizationMongoTemplate")
	private MongoTemplate mongoTemplate;

	@Override
	public Optional<WorkplacementLawson> findFirstByActive(String jobTypeId) {
		String companyId = ServletRequestAWS.getCompanyId();
		Query query = new Query(Criteria.where("_id").is(jobTypeId)
				.and("company._id").is(companyId)
				.and("status").is(StatusData.ACTIVE));
		return Optional.ofNullable(mongoTemplate.findOne(query, WorkplacementLawson.class));
	}

	@Override
	public Page<WorkplacementLawson> findByDataTables(PaginationLWSParams param) {
		String companyId = ServletRequestAWS.getCompanyId();
		String keyWord = param.getQ();
		String areaId = param.getAreaId();
		Criteria keywordCriteria = new Criteria();
		Query query = new Query();

		Criteria searchCriteria = new Criteria();
		Criteria companyCriteria = Criteria.where("company._id").is(companyId);
		Criteria statusCriteria = Criteria.where("status").is(StatusData.ACTIVE);

		query.addCriteria(companyCriteria);
		query.addCriteria(statusCriteria);

		if(keyWord != null) {
			String escapedKeyword = Pattern.quote(keyWord);
			keywordCriteria = Criteria.where("name").regex(escapedKeyword, "i");
			query.addCriteria(keywordCriteria);
		}

		if(areaId != null) {
			Criteria areaCriteria = Criteria.where("workArea._id").is(areaId);
			query.addCriteria(areaCriteria);
		}

		int page = param.getPage() - 1;
		Pageable pageable = PageRequest.of(
				page, param.getLimit(),
				param.getSortDirection(), param.getSortedField().getDatabaseFieldName()
		);

		searchCriteria.andOperator(companyCriteria, statusCriteria, keywordCriteria);

		Query queryBuilder = Query.query(searchCriteria).with(pageable);

		query.with(pageable);
		long total = mongoTemplate.count(Query.query(searchCriteria), WorkplacementLawson.class);

		List<WorkplacementLawson> resultList = mongoTemplate.find(queryBuilder, WorkplacementLawson.class);
		return new PageImpl<>(resultList, pageable, total);
	}
}