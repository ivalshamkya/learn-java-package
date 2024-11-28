package com.jobseeker.hrms.organization.service.basic.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.company.CompanyDataInitRequest;
import com.jobseeker.hrms.organization.data.basic.company.CompanyDataResponse;
import com.jobseeker.hrms.organization.data.basic.company.CompanyDataSimpleRequest;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.interfaces.ICompanyHandler;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.service.baseContract.BaseCompany;
import com.jobseeker.hrms.organization.service.basic.branch.query.GetBranch;
import com.jobseeker.hrms.organization.service.basic.company.command.InitCompany;
import com.jobseeker.hrms.organization.service.basic.company.command.UpdateCompany;
import com.jobseeker.hrms.organization.service.basic.company.query.GetCompany;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@SuppressWarnings("unchecked")
public class CompanyOrgService<T> extends BaseCompany<T> {

	//<editor-fold desc="getCompany">
	@Autowired
	@Qualifier("getCompany")
	private GetCompany getCompany;

	@Override
	public T getCompany() {
		return (T) getCompany.execute();
	}
	//</editor-fold>

	//<editor-fold desc="initCompany">
	@Autowired
	@Qualifier("initCompany")
	private InitCompany initCompany;

	@Override
	public T initCompany(Map<Object, Object> request) {
		CompanyDataInitRequest dataRequest = RequestHandler.remapToData(request, CompanyDataInitRequest.class);
		return (T) initCompany.execute(dataRequest);
	}
	//</editor-fold>

	//<editor-fold desc="updateCompany">
	@Autowired
	@Qualifier("updateCompany")
	private UpdateCompany updateCompany;

	@Override
	public T updateCompany(Map<Object, Object> request) {
		CompanyDataSimpleRequest dataRequest = RequestHandler.remapToData(request, CompanyDataSimpleRequest.class);
		return (T) updateCompany.execute(dataRequest);
	}
	//</editor-fold>
}
