package com.jobseeker.hrms.organization.repositories.basic.organization.emailTemplate;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationParams;
import org.jobseeker.organization.EmailTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.jobseeker.enums.general.StatusData;
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
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailTemplateOrgRepositoryImpl implements EmailTemplateOrgRepositoryExtend {
	@Autowired
	@Qualifier("organizationMongoTemplate")
	private MongoTemplate mongoTemplate;

	@Override
	public Optional<EmailTemplate> findFirstByActive(String jobTypeId) {
		String companyId = ServletRequestAWS.getCompanyId();
		Query query = new Query(Criteria.where("_id").is(jobTypeId)
				.and("company._id").is(companyId)
				.and("status").is(StatusData.ACTIVE));
		return Optional.ofNullable(mongoTemplate.findOne(query, EmailTemplate.class));
	}

	@Override
	public Page<EmailTemplate> findByDataTables(PaginationParams param) {
		String companyId = ServletRequestAWS.getCompanyId();

		Criteria searchCriteria = new Criteria();
		Criteria companyCriteria = Criteria.where("company._id").is(new ObjectId(companyId));
		Criteria statusCriteria = Criteria.where("status").is(StatusData.ACTIVE);

		String keyWord = param.getQ();
		Criteria keywordCriteria = new Criteria();
		if(!Objects.equals(keyWord, "")) {
			String escapedKeyword = Pattern.quote(keyWord);
			Criteria regexName = Criteria.where("name").regex(escapedKeyword, "i");
			keywordCriteria.orOperator(regexName);
		}

		int page = param.getPage() - 1;
		Pageable pageable = PageRequest.of(
				page, param.getLimit(),
				param.getSortDirection(), param.getSortedField().getDatabaseFieldName()
		);

		searchCriteria.andOperator(
				companyCriteria, statusCriteria, keywordCriteria);
		Query queryBuilder = Query.query(searchCriteria).with(pageable);

		List<EmailTemplate> resultList = mongoTemplate.find(queryBuilder, EmailTemplate.class);
		long total = mongoTemplate.count(Query.query(searchCriteria), EmailTemplate.class);

		return new PageImpl<>(resultList, pageable, total);
	}
}