package com.jobseeker.hrms.organization.repositories.basic.organization.customSource;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.CustomSource;
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

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomSourceOrgRepositoryImpl implements CustomSourceOrgRepositoryExtend {

	@Autowired
	@Qualifier("organizationMongoTemplate")
	private MongoTemplate mongoTemplate;

	@Override
	public Optional<CustomSource> findFirstByActive(String jobTypeId) {
		String companyId = ServletRequestAWS.getCompanyId();
		Query query = new Query(Criteria.where("_id").is(jobTypeId)
				.and("company._id").is(companyId)
				.and("status").is(StatusData.ACTIVE));
		return Optional.ofNullable(mongoTemplate.findOne(query, CustomSource.class));
	}

	@Override
	public Page<CustomSource> findByDataTables(PaginationParams param) {
		String companyId = ServletRequestAWS.getCompanyId();

		Criteria searchCriteria = new Criteria();
		Criteria companyCriteria = Criteria.where("company._id").is(new ObjectId(companyId));
//		Criteria statusCriteria = Criteria.where("status").is(StatusData.ACTIVE);
//
//		String keyWord = param.getQ();
//		Criteria keywordCriteria = new Criteria();
//		if(!Objects.equals(keyWord, "")) {
//			Criteria regexName = Criteria.where("name").regex(keyWord, "i");
//			keywordCriteria.orOperator(regexName);
//		}

		int page = param.getPage() - 1;
		Pageable pageable = PageRequest.of(
				page, param.getLimit(),
				param.getSortDirection(), param.getSortedField().getDatabaseFieldName()
		);

		searchCriteria.andOperator(
				companyCriteria);
//				companyCriteria, statusCriteria, keywordCriteria);
		Query queryBuilder = Query.query(searchCriteria).with(pageable);

		List<CustomSource> resultList = mongoTemplate.find(queryBuilder, CustomSource.class);
		long total = mongoTemplate.count(Query.query(searchCriteria), CustomSource.class);

		return new PageImpl<>(resultList, pageable, total);
	}
}