package com.jobseeker.hrms.candidate.services;

import com.jobseeker.hrms.candidate.interfaces.ICareersiteHandler;

import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class BaseCareersiteService<T> implements ICareersiteHandler<T> {

    @Override
    public T createCandidate(Map<Object, Object> request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T updateCandidate(Map<Object, Object> request, String step) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T getCandidateProfile() {
        throw new UnsupportedOperationException("Not supported yet");
    }
}

