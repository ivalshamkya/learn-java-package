package com.jobseeker.hrms.organization.service.sms.form.query;

import com.jobseeker.hrms.organization.data.sms.form.FormDataResponse;
import com.jobseeker.hrms.organization.repositories.sms.organization.form.FormSmsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Qualifier("getFormByCode")
public class SMSGetFormByCode {

    private final FormSmsRepository formRepository;

    public List<FormDataResponse> execute(String code) {
        return formRepository.findByCode(code);
    }
}
