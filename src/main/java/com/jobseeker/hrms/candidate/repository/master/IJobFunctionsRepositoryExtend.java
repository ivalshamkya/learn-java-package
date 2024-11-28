package com.jobseeker.hrms.candidate.repository.master;

import java.util.List;

public interface IJobFunctionsRepositoryExtend {

    List<String> findJobFunctionRelatedToCategory(String categoryJobFunctionId);
}
