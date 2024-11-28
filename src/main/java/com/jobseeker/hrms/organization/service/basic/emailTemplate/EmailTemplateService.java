package com.jobseeker.hrms.organization.service.basic.emailTemplate;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.emailTemplate.EmailTemplateDataResponse;
import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationParams;
import com.jobseeker.hrms.organization.mapper.basic.IEmailTemplateMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.emailTemplate.EmailTemplateOrgRepository;
import com.jobseeker.hrms.organization.service.baseContract.BaseEmailTemplate;
import com.jobseeker.hrms.organization.service.basic.branch.query.GetBranches;
import com.jobseeker.hrms.organization.service.basic.emailTemplate.query.GetEmailTemplates;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class EmailTemplateService<T> extends BaseEmailTemplate<T> {

	//<editor-fold desc="getEmailTemplates">
	@Autowired
	@Qualifier("getEmailTemplates")
	private GetEmailTemplates getEmailTemplates;

	@Override
	public Page<T> getEmailTemplates(Map<Object, Object> param) {
		return (Page<T>) getEmailTemplates.execute(param);
	}
	//</editor-fold>
}
