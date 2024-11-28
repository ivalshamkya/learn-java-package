package com.jobseeker.hrms.organization.service.lws.candidateLanguage;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import com.jobseeker.hrms.organization.service.baseContract.BaseCandidateLanguage;
import com.jobseeker.hrms.organization.service.lws.candidateLanguage.query.LawsonGetCandidateLanguagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class CandidateLanguageLawsonService<T> extends BaseCandidateLanguage<T> {
    //<editor-fold desc="lawsonGetCandidateLanguagesService">
    @Autowired
    @Qualifier("lawsonGetCandidateLanguages")
    private LawsonGetCandidateLanguagesService lawsonGetCandidateLanguagesService;

    @Override
    public Page<T> getCandidateLanguages(Map<Object, Object> param) {
        PaginationLWSParams paginationParam = RequestHandler.remapToData(param, PaginationLWSParams.class);
        return (Page<T>) lawsonGetCandidateLanguagesService.execute(paginationParam);
    }
    //</editor-fold>

}
