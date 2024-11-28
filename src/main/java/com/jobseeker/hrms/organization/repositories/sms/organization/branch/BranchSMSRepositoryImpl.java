package com.jobseeker.hrms.organization.repositories.sms.organization.branch;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.sms.branch.PaginationBranchSmsParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.sms.BranchSMS;
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

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class BranchSMSRepositoryImpl implements BranchSMSRepositoryExtend {

	@Autowired
	@Qualifier("organizationMongoTemplate")
	private MongoTemplate mongoTemplate;

	@Override
	public Optional<BranchSMS> findFirstByActive(String branchSMSId) {
		String companyId = ServletRequestAWS.getCompanyId();
		Query query = new Query(Criteria.where("_id").is(branchSMSId)
				.and("company._id").is(companyId)
				.and("deleted_at").is(null));
		return Optional.ofNullable(mongoTemplate.findOne(query, BranchSMS.class));
	}

	@Override
	public Page<BranchSMS> findByDataTables(PaginationBranchSmsParams param) {
		String companyId = ServletRequestAWS.getCompanyId();
		Query query = new Query();

		query.addCriteria(Criteria.where("company._id").is(companyId));
		query.addCriteria(Criteria.where("status").is(StatusData.ACTIVE));
		if(param.getWorkAreaIds() != null){
			List<ObjectId> workAreasId = Arrays.stream(param.getWorkAreaIds()
				.split(","))
				.map(ObjectId::new)
				.toList();
			query.addCriteria(Criteria.where("work_area._id").in(workAreasId));
		}

		String keyWord = param.getQ();
		Criteria keywordCriteria = new Criteria();
		if(keyWord != null) {
			String escapedKeyword = Pattern.quote(keyWord);
			Criteria regexCode = Criteria.where("code").regex(escapedKeyword, "i");
			Criteria regexName = Criteria.where("name").regex(escapedKeyword, "i");
			// Use Criteria.orOperator to combine the regex conditions
			Criteria combinedCriteria = new Criteria().orOperator(regexCode, regexName);
			// Add the combined criteria to the query
			query.addCriteria(combinedCriteria);
		}
		String[] sortByFields = {"code"};

		int page = param.getPage() - 1;
		Pageable pageable = PageRequest.of(
				page, param.getLimit(),
				param.getSortDirection(), sortByFields
		);
		long total = mongoTemplate.count(query, BranchSMS.class);
		query.with(pageable);

		List<BranchSMS> resultList = mongoTemplate.find(query, BranchSMS.class);

		return new PageImpl<>(resultList, pageable, total);
	}
}
