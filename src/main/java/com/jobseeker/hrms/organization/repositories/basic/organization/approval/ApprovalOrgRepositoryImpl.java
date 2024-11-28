package com.jobseeker.hrms.organization.repositories.basic.organization.approval;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.approval.ApprovalDatatableDataRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Approval;
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
public class ApprovalOrgRepositoryImpl implements ApprovalOrgRepositoryExtend {

	@Autowired
	@Qualifier("organizationMongoTemplate")
	private MongoTemplate mongoTemplate;

	@Override
	public Optional<Approval> findFirstByActive(String approvalId) {
		return Optional.empty();
	}

	@Override
	public Page<Approval> findByDataTables(ApprovalDatatableDataRequest param) {
		String companyId = ServletRequestAWS.getCompanyId();

		Criteria searchCriteria = new Criteria();
		Criteria companyCriteria = Criteria.where("company._id").is(companyId);
		Criteria typeCriteria = Criteria.where("type").is(param.getType());
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
				companyCriteria, typeCriteria, statusCriteria, keywordCriteria);
		Query queryBuilder = Query.query(searchCriteria).with(pageable);

		List<Approval> resultList = mongoTemplate.find(queryBuilder, Approval.class);
		long total = mongoTemplate.count(Query.query(searchCriteria), Approval.class);

		return new PageImpl<>(resultList, pageable, total);
	}

	@Override
	public Page<Approval> findAllByActive(Pageable pageable) {
		String companyId = ServletRequestAWS.getCompanyId();

		Criteria searchCriteria = new Criteria();
		Criteria companyCriteria = Criteria.where("company._id").is(companyId);
		Criteria statusCriteria = Criteria.where("status").is(StatusData.ACTIVE);

		searchCriteria.andOperator(companyCriteria, statusCriteria);
		Query queryBuilder = Query.query(searchCriteria).with(pageable);

		List<Approval> resultList = mongoTemplate.find(queryBuilder, Approval.class);
		long total = mongoTemplate.count(Query.query(searchCriteria), Approval.class);

		return new PageImpl<>(resultList, pageable, total);
	}

	@Override
	public List<Approval> findAllByActive() {
		String companyId = ServletRequestAWS.getCompanyId();

		Criteria searchCriteria = new Criteria();
		Criteria companyCriteria = Criteria.where("company._id").is(companyId);
		Criteria statusCriteria = Criteria.where("status").is(StatusData.ACTIVE);

		searchCriteria.andOperator(companyCriteria, statusCriteria);
		Query queryBuilder = Query.query(searchCriteria);

		List<Approval> resultList = mongoTemplate.find(queryBuilder, Approval.class);
		return resultList;
	}

	@Override
	public void deleteByEmployeeId(List<String> employeeId) {

	}
}
