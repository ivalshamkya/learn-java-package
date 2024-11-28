package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.ICandidateLanguageHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

public abstract class BaseCandidateLanguage<T> implements ICandidateLanguageHandler<T> {
    @Override
    public Page<T> getCandidateLanguages(Map<Object, Object> request) {
        throw new UnsupportedOperationException("Get candidate languages not supported yet.");
    }
}
