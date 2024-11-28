package com.jobseeker.hrms.organization.repositories.lws.organization.workArea;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.lawson.WorkAreaLawson;
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
public class WorkAreaOrgLWSRepositoryImpl implements WorkAreaOrgLWSRepositoryExtend {

	@Autowired
	@Qualifier("organizationMongoTemplate")
	private MongoTemplate mongoTemplate;

	@Override
	public Optional<WorkAreaLawson> findFirstByActive(String jobTypeId) {
		String companyId = ServletRequestAWS.getCompanyId();
		Query query = new Query(Criteria.where("_id").is(jobTypeId)
				.and("company._id").is(companyId)
				.and("status").is(StatusData.ACTIVE));
		return Optional.ofNullable(mongoTemplate.findOne(query, WorkAreaLawson.class));
	}

	@Override
	public Page<WorkAreaLawson> findByDataTables(PaginationLWSParams param) {
		String companyId = ServletRequestAWS.getCompanyId();
		String keyWord = param.getQ();
		String branchId = param.getBranchId();
		Criteria keywordCriteria = new Criteria();
		Query query = new Query();
		
		Criteria searchCriteria = new Criteria();
		Criteria companyCriteria = Criteria.where("company._id").is(companyId);
		Criteria statusCriteria = Criteria.where("status").is(StatusData.ACTIVE);
		Criteria branchCriteria = new Criteria();
		

		query.addCriteria(companyCriteria);
		query.addCriteria(statusCriteria);

		if(keyWord != null) {
			String escapedKeyword = Pattern.quote(keyWord);
			keywordCriteria = new Criteria().orOperator(
					Criteria.where("name").regex(escapedKeyword, "i"),
					Criteria.where("branch.name").regex(escapedKeyword, "i")
			);
			query.addCriteria(keywordCriteria);
		}

		if(branchId != null) {
			branchCriteria = Criteria.where("branch._id").is(branchId);
			query.addCriteria(branchCriteria);
		}

		int page = param.getPage() - 1;
		Pageable pageable = PageRequest.of(
				page, param.getLimit(),
				param.getSortDirection(), param.getSortedField().getDatabaseFieldName()
		);
		
		searchCriteria.andOperator(
				companyCriteria, statusCriteria, keywordCriteria, branchCriteria);
		Query queryBuilder = Query.query(searchCriteria).with(pageable);

		List<WorkAreaLawson> resultList = mongoTemplate.find(queryBuilder, WorkAreaLawson.class);
		long total = mongoTemplate.count(Query.query(searchCriteria), WorkAreaLawson.class);

		return new PageImpl<>(resultList, pageable, total);
	}
}