package com.jobseeker.hrms.organization.repositories.basic.organization.recruitmentStage;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobseeker.data.PaginationParam;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.RecruitmentStage;
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
public class RecruitmentStageOrgRepositoryImpl implements RecruitmentStageOrgRepositoryExtend {
	@Autowired
	@Qualifier("organizationMongoTemplate")
	private MongoTemplate mongoTemplate;

	@Override
	public Optional<RecruitmentStage> findFirstByActive(String jobTypeId) {
		String companyId = ServletRequestAWS.getCompanyId();
		Query query = new Query(Criteria.where("_id").is(jobTypeId)
				.and("company._id").is(companyId)
				.and("status").is(StatusData.ACTIVE.getLabel()));
		return Optional.ofNullable(mongoTemplate.findOne(query, RecruitmentStage.class));
	}

	@Override
	public Page<RecruitmentStage> findByDataTables(PaginationParam param) {
		String companyId = ServletRequestAWS.getCompanyId();

		Criteria searchCriteria = new Criteria();
		Criteria companyCriteria = Criteria.where("company._id").is(companyId);
		Criteria statusCriteria = Criteria.where("status").is(StatusData.ACTIVE);

		String keyWord = param.getQ();
		Criteria keywordCriteria = new Criteria();
		if(keyWord != null) {
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

		List<RecruitmentStage> resultList = mongoTemplate.find(queryBuilder, RecruitmentStage.class);
		long total = mongoTemplate.count(Query.query(searchCriteria), RecruitmentStage.class);

		return new PageImpl<>(resultList, pageable, total);
	}
}