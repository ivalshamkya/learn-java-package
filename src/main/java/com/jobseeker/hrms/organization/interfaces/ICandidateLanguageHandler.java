package com.jobseeker.hrms.organization.interfaces;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface ICandidateLanguageHandler<T> {
    Page<T> getCandidateLanguages(Map<Object, Object> request);
}
