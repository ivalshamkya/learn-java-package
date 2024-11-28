package com.jobseeker.hrms.candidate.interfaces;

import java.util.Map;

public interface ICareersiteHandler<T> {

    T createCandidate(Map<Object, Object> request);

    T updateCandidate(Map<Object, Object> request, String step);

    T getCandidateProfile();
}
