package com.jobseeker.hrms.organization.service.sms.form;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.sms.branch.PaginationBranchSmsParams;
import com.jobseeker.hrms.organization.data.sms.form.FormDataResponse;
import com.jobseeker.hrms.organization.interfaces.IFormHandler;
import com.jobseeker.hrms.organization.repositories.sms.organization.form.FormSmsRepository;
import com.jobseeker.hrms.organization.service.baseContract.BaseForm;
import com.jobseeker.hrms.organization.service.sms.branch.query.SMSGetBranches;
import com.jobseeker.hrms.organization.service.sms.form.query.SMSGetFormByCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class FormSMSService<T> extends BaseForm<T> {

	//<editor-fold desc="getFormByCode">
	@Autowired
	@Qualifier("getFormByCode")
	private SMSGetFormByCode getFormByCode;

	@Override
	public List<T> getFormByCode(String oid) {
		return (List<T>) getFormByCode.execute(oid);
	}
	//</editor-fold>
}
