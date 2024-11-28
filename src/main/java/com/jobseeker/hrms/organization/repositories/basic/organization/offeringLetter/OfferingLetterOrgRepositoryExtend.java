package com.jobseeker.hrms.organization.repositories.basic.organization.offeringLetter;

import org.jobseeker.organization.OfferingLetter;

import java.util.Optional;

public interface OfferingLetterOrgRepositoryExtend {

	Optional<OfferingLetter> findFirstByActive();

}
