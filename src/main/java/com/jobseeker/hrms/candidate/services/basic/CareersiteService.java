package com.jobseeker.hrms.candidate.services.basic;

import com.jobseeker.hrms.candidate.services.BaseCareersiteService;
import com.jobseeker.hrms.candidate.services.basic.command.CreateCandidate;
import com.jobseeker.hrms.candidate.services.basic.command.UpdateCandidate;
import com.jobseeker.hrms.candidate.services.basic.query.GetCandidateProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class CareersiteService<T> extends BaseCareersiteService<T> {

    //<editor-fold desc="createCandidate">
    @Autowired
    @Qualifier("createCandidate")
    private CreateCandidate createCandidate;

    @Override
    public T createCandidate(Map<Object, Object> request) {
        return (T) createCandidate.execute(request);
    }
    //</editor-fold>

    //<editor-fold desc="updateCandidate">
    @Autowired
    @Qualifier("updateCandidate")
    private UpdateCandidate updateCandidate;

    @Override
    public T updateCandidate(Map<Object, Object> request, String step) {
        return (T) updateCandidate.execute(request, step);
    }
    //</editor-fold>

    //<editor-fold desc="getCandidateProfile">
    @Autowired
    @Qualifier("getCandidateProfile")
    private GetCandidateProfile getCandidateProfile;

    @Override
    public T getCandidateProfile() {
        return (T) getCandidateProfile.execute();
    }
    //</editor-fold>

}
