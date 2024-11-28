package com.jobseeker.hrms.organization.repositories.basic.organization.offeringLetter;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.OfferingLetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OfferingLetterOrgRepositoryImpl implements OfferingLetterOrgRepositoryExtend {
	@Autowired
	@Qualifier("organizationMongoTemplate")
	private MongoTemplate mongoTemplate;

	@Override
	public Optional<OfferingLetter> findFirstByActive() {
		String companyId = ServletRequestAWS.getCompanyId();
		Query query = new Query(Criteria.where("company._id").is(companyId)
				.and("status").is(StatusData.ACTIVE));
		return Optional.ofNullable(mongoTemplate.findOne(query, OfferingLetter.class));
	}

}