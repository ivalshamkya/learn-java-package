package com.jobseeker.hrms.organization.service.basic.offeringLetter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.offeringLetter.OfferingLetterDataRequest;
import com.jobseeker.hrms.organization.data.basic.offeringLetter.OfferingLetterDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.interfaces.IOfferingLetterHandler;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.basic.IOfferingLetterMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.offeringLetter.OfferingLetterOrgRepository;
import com.jobseeker.hrms.organization.service.baseContract.BaseOfferingLetter;
import com.jobseeker.hrms.organization.service.basic.jobLevel.query.GetJobLevel;
import com.jobseeker.hrms.organization.service.basic.offeringLetter.command.UpdateOfferingLetter;
import com.jobseeker.hrms.organization.service.basic.offeringLetter.query.GetOfferingLetter;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.OfferingLetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@SuppressWarnings("unchecked")
public class OfferingLetterService<T> extends BaseOfferingLetter<T> {

	//<editor-fold desc="getOfferingLetter">
	@Autowired
	@Qualifier("getOfferingLetter")
	private GetOfferingLetter getOfferingLetter;

	@Override
	public T getOfferingLetter() {
		return (T) getOfferingLetter.execute();
	}
	//</editor-fold>

	//<editor-fold desc="updateOfferingLetter">
	@Autowired
	@Qualifier("updateOfferingLetter")
	private UpdateOfferingLetter updateOfferingLetter;

	@Override
	public T updateOfferingLetter(Map<Object, Object> request) {
		OfferingLetterDataRequest dataRequest = RequestHandler.remapToData(request, OfferingLetterDataRequest.class);
		return (T) updateOfferingLetter.execute(dataRequest);
	}
	//</editor-fold>
}
