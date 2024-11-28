package com.jobseeker.hrms.organization.repositories.basic.organization.documentRequest;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationBasicParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.DocumentRequest;
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
public class DocumentRequestOrgRepositoryImpl implements DocumentRequestOrgRepositoryExtend {
	@Autowired
	@Qualifier("organizationMongoTemplate")
	private MongoTemplate mongoTemplate;

	@Override
	public Optional<DocumentRequest> findFirstByActive(String jobTypeId) {
		String companyId = ServletRequestAWS.getCompanyId();
		Query query = new Query(Criteria.where("_id").is(jobTypeId)
				.and("company._id").is(companyId)
				.and("status").is(StatusData.ACTIVE));
		return Optional.ofNullable(mongoTemplate.findOne(query, DocumentRequest.class));
	}

	@Override
	public Page<DocumentRequest> findByDataTables(PaginationBasicParams param) {
		String companyId = ServletRequestAWS.getCompanyId();

		Criteria searchCriteria = new Criteria();
		Criteria companyCriteria = Criteria.where("company._id").is(companyId);
		Criteria statusCriteria = Criteria.where("status").is(StatusData.ACTIVE);

		String keyWord = param.getQ();
		String groupCode = param.getGroupCode();
		Criteria keywordCriteria = new Criteria();

		if(keyWord != null) {
			String escapedKeyword = Pattern.quote(keyWord);
			Criteria regexName = Criteria.where("name").regex(escapedKeyword, "i");
			keywordCriteria.orOperator(regexName);
		}

		if(groupCode != null) {
			Criteria groupCodeCriteria = Criteria.where("groupCode").is(groupCode);
			keywordCriteria.orOperator(groupCodeCriteria);
		}

		int page = param.getPage() - 1;
		Pageable pageable = PageRequest.of(
				page, param.getLimit(),
				param.getSortDirection(), param.getSortedField().getDatabaseFieldName()
		);

		searchCriteria.andOperator(
				companyCriteria, statusCriteria, keywordCriteria);
		Query queryBuilder = Query.query(searchCriteria).with(pageable);

		List<DocumentRequest> resultList = mongoTemplate.find(queryBuilder, DocumentRequest.class);
		long total = mongoTemplate.count(Query.query(searchCriteria), DocumentRequest.class);

		return new PageImpl<>(resultList, pageable, total);
	}
}