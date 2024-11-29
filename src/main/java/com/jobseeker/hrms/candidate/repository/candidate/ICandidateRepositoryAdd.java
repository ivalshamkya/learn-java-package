package com.jobseeker.hrms.candidate.repository.candidate;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ICandidateRepositoryAdd<T> {

    Page<T> findCandidates(Map<Object, Object> params, List<String> blacklistedIds);

    List<T> findCandidatesExcel(Map<Object, Object> params);
}
