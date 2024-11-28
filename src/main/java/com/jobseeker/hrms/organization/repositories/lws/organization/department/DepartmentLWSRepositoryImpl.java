package com.jobseeker.hrms.organization.repositories.lws.organization.department;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.lawson.DepartmentLawson;
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
import java.util.regex.Pattern;

@Slf4j
@Component
@RequiredArgsConstructor
public class DepartmentLWSRepositoryImpl implements DepartmentLWSRepositoryExtend {
	@Autowired
	@Qualifier("organizationMongoTemplate")
	private MongoTemplate mongoTemplate;

	@Override
	public Page<DepartmentLawson> findByDataTables(PaginationLWSParams param) {
		String companyId = ServletRequestAWS.getCompanyId();

		Criteria searchCriteria = new Criteria();
		Criteria companyCriteria = Criteria.where("company._id").is(companyId);
		Criteria statusCriteria = Criteria.where("status").is(StatusData.ACTIVE);

		String keyWord = param.getQ();
		String divisionId = param.getDivisionId();
		Criteria keywordCriteria = new Criteria();
		if(keyWord != null) {
			String escapedKeyword = Pattern.quote(keyWord);
			Criteria regexName = Criteria.where("name").regex(escapedKeyword, "i");
			keywordCriteria.orOperator(regexName);
		}

		if(divisionId != null) {
			Criteria branchCriteria = Criteria.where("division._id").is(divisionId);
			keywordCriteria.orOperator(branchCriteria);
		}

		int page = param.getPage() - 1;
		Pageable pageable = PageRequest.of(
				page, param.getLimit(),
				param.getSortDirection(), param.getSortedField().getDatabaseFieldName()
		);

		searchCriteria.andOperator(
				companyCriteria, statusCriteria, keywordCriteria);
		Query queryBuilder = Query.query(searchCriteria).with(pageable);

		List<DepartmentLawson> resultList = mongoTemplate.find(queryBuilder, DepartmentLawson.class);
		long total = mongoTemplate.count(Query.query(searchCriteria), DepartmentLawson.class);

		return new PageImpl<>(resultList, pageable, total);
	}


}