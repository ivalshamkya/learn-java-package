package com.jobseeker.hrms.organization.service.sms.pkwtTemplate;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.sms.branch.PaginationBranchSmsParams;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.repositories.sms.organization.pkwtTemplate.PkwtTemplateOrgSmsRepository;
import com.jobseeker.hrms.organization.service.baseContract.BasePkwtTemplate;
import com.jobseeker.hrms.organization.service.sms.branch.query.SMSGetBranches;
import com.jobseeker.hrms.organization.service.sms.pkwtTemplate.query.SMSGetPkwtTemplate;
import com.jobseeker.hrms.organization.service.sms.pkwtTemplate.query.SMSGetPkwtTemplates;
import lombok.RequiredArgsConstructor;
import org.jobseeker.organization.sms.PkwtTemplateSMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@SuppressWarnings("unchecked")
public class PkwtTemplateSMSService<T> extends BasePkwtTemplate<T> {

	//<editor-fold desc="getPkwtTemplate">
	@Autowired
	@Qualifier("SMSGetPkwtTemplate")
	private SMSGetPkwtTemplate getPkwtTemplate;

	@Override
	public T getPkwtTemplate(String oid) {
		return (T) getPkwtTemplate.execute(oid);
	}
	//</editor-fold>

	//<editor-fold desc="getPkwtTemplates">
	@Autowired
	@Qualifier("SMSGetPkwtTemplates")
	private SMSGetPkwtTemplates getPkwtTemplates;

	@Override
	public List<T> getPkwtTemplates() {
		return (List<T>) getPkwtTemplates.execute();
	}
	//</editor-fold>
}
