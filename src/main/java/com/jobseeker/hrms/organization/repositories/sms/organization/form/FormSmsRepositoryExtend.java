package com.jobseeker.hrms.organization.repositories.sms.organization.form;

import com.jobseeker.hrms.organization.data.sms.form.FormDataResponse;

import java.util.List;

public interface FormSmsRepositoryExtend {
	List<FormDataResponse> findByCode(String code);
}
