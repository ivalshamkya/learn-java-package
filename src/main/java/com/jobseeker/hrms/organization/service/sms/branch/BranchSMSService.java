package com.jobseeker.hrms.organization.service.sms.branch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.branch.BranchDataRequest;
import com.jobseeker.hrms.organization.data.sms.branch.BranchSmsDataRequest;
import com.jobseeker.hrms.organization.data.sms.branch.BranchSmsDataResponse;
import com.jobseeker.hrms.organization.data.sms.branch.PaginationBranchSmsParams;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.interfaces.IBranchHandler;
import com.jobseeker.hrms.organization.mapper.basic.IBranchMapper;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.sms.organization.branch.BranchSMSRepository;
import com.jobseeker.hrms.organization.service.baseContract.BaseBranch;
import com.jobseeker.hrms.organization.service.basic.branch.command.CreateBranch;
import com.jobseeker.hrms.organization.service.basic.branch.command.DeleteBranch;
import com.jobseeker.hrms.organization.service.basic.branch.command.UpdateBranch;
import com.jobseeker.hrms.organization.service.basic.branch.query.GetBranch;
import com.jobseeker.hrms.organization.service.basic.branch.query.GetBranches;
import com.jobseeker.hrms.organization.service.sms.branch.command.SMSCreateBranch;
import com.jobseeker.hrms.organization.service.sms.branch.command.SMSDeleteBranch;
import com.jobseeker.hrms.organization.service.sms.branch.command.SMSUpdateBranch;
import com.jobseeker.hrms.organization.service.sms.branch.query.SMSGetBranch;
import com.jobseeker.hrms.organization.service.sms.branch.query.SMSGetBranches;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.MessageResponse;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.helper.ObjectMapperHelper;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.sms.BranchSMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@SuppressWarnings("unchecked")
public class BranchSMSService<T> extends BaseBranch<T> {

	//<editor-fold desc="getBranch">
	@Autowired
	@Qualifier("SMSGetBranch")
	private SMSGetBranch getBranch;

	@Override
	public T getBranch(String oid) {
		return (T) getBranch.execute(oid);
	}
	//</editor-fold>

	//<editor-fold desc="getBranches">
	@Autowired
	@Qualifier("SMSGetBranches")
	private SMSGetBranches getBranches;

	@Override
	public Page<T> getBranches(Map<Object, Object> param) {
		PaginationBranchSmsParams dataRequest = RequestHandler.remapToData(param, PaginationBranchSmsParams.class);
		return (Page<T>) getBranches.execute(dataRequest);
	}
	//</editor-fold>

	//<editor-fold desc="createBranch">
	@Autowired
	@Qualifier("SMSCreateBranch")
	private SMSCreateBranch createBranch;

	@Override
	public T createBranch(Map<Object, Object> param) {
		BranchSmsDataRequest dataRequest = RequestHandler.remapToData(param, BranchSmsDataRequest.class);
		return (T) createBranch.execute(dataRequest);
	}
	//</editor-fold>

	//<editor-fold desc="updateBranch">
	@Autowired
	@Qualifier("SMSUpdateBranch")
	private SMSUpdateBranch updateBranch;

	@Override
	public T updateBranch(Map<Object, Object> request, String oid) {
		BranchSmsDataRequest dataRequest = RequestHandler.remapToData(request, BranchSmsDataRequest.class);
		return (T) updateBranch.execute(dataRequest, oid);
	}
	//</editor-fold>

	//<editor-fold desc="deleteBranch">
	@Autowired
	@Qualifier("SMSDeleteBranch")
	private SMSDeleteBranch deleteBranch;

	@Override
	public String deleteBranch(String oid) {
		return deleteBranch.execute(oid);
	}
	//</editor-fold>
}
