package com.jobseeker.hrms.organization.service.sms.vacancyTemplate;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.sms.jobTemplate.JobTemplateDataRequest;
import com.jobseeker.hrms.organization.service.baseContract.BaseVacancyTemplate;
import com.jobseeker.hrms.organization.service.sms.vacancyTemplate.command.SMSCreateJobTemplate;
import com.jobseeker.hrms.organization.service.sms.vacancyTemplate.command.SMSDeleteJobTemplate;
import com.jobseeker.hrms.organization.service.sms.vacancyTemplate.command.SMSUpdateJobTemplate;
import com.jobseeker.hrms.organization.service.sms.vacancyTemplate.query.SMSGetJobTemplate;
import com.jobseeker.hrms.organization.service.sms.vacancyTemplate.query.SMSGetJobTemplates;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class SMSVacancyTemplateService<T, U> extends BaseVacancyTemplate<T, U> {

	//<editor-fold desc="getJobTemplate">
	@Autowired
	@Qualifier("SMSGetJobTemplate")
	private SMSGetJobTemplate SMSGetJobTemplate;

	@Override
	public U getJobTemplate(String oid) {
		return (U) SMSGetJobTemplate.execute(oid);
	}
	//</editor-fold>

	//<editor-fold desc="getJobTemplates">
	@Autowired
	@Qualifier("SMSGetJobTemplates")
	private SMSGetJobTemplates SMSGetJobTemplates;

	@Override
	public Page<T> getJobTemplates(Map<Object, Object> param) {
		PaginationParam dataRequest = RequestHandler.remapToData(param, PaginationParam.class);
		return (Page<T>) SMSGetJobTemplates.execute(dataRequest);
	}
	//</editor-fold>

	//<editor-fold desc="createJobTemplate">
	@Autowired
	@Qualifier("SMSCreateJobTemplate")
	private SMSCreateJobTemplate SMSCreateJobTemplate;

	@Override
	public U createJobTemplate(Map<Object, Object> param) {
		JobTemplateDataRequest dataRequest = RequestHandler.remapToData(param, JobTemplateDataRequest.class);
		return (U) SMSCreateJobTemplate.execute(dataRequest);
	}
	//</editor-fold>

	//<editor-fold desc="updateJobTemplate">
	@Autowired
	@Qualifier("SMSUpdateJobTemplate")
	private SMSUpdateJobTemplate SMSUpdateJobTemplate;

	@Override
	public U updateJobTemplate(Map<Object, Object> request, String oid) {
		JobTemplateDataRequest dataRequest = RequestHandler.remapToData(request, JobTemplateDataRequest.class);
		return (U) SMSUpdateJobTemplate.execute(dataRequest, oid);
	}
	//</editor-fold>

	//<editor-fold desc="deleteJobTemplate">
	@Autowired
	@Qualifier("SMSDeleteJobTemplate")
	private SMSDeleteJobTemplate SMSDeleteJobTemplate;

	@Override
	public String deleteJobTemplate(String oid) {
		return SMSDeleteJobTemplate.execute(oid);
	}
	//</editor-fold>
}
