package com.jobseeker.hrms.organization.service.basic.recruitmentStage.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.recruitmentStage.RecruitmentStageDataRequest;
import com.jobseeker.hrms.organization.data.basic.recruitmentStage.RecruitmentStageDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.basic.IRecruitmentStageMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.recruitmentStage.RecruitmentStageOrgRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.RecruitmentStage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("updateRecruitmentStage")
public class UpdateRecruitmentStage {

    private final CompanyOrgRepository companyRepository;
    private final RecruitmentStageOrgRepository recruitmentStageOrgRepository;

    private final ICompanyMapper companyMapper;
    private final IRecruitmentStageMapper recruitmentStageMapper;

    private final Validator validator;

    public RecruitmentStageDataResponse execute(RecruitmentStageDataRequest request, String oid) {
        RecruitmentStage recruitmentStage = composeBranch(request, oid);

        return recruitmentStageMapper.toRecruitmentStageDataResponse(recruitmentStage);
    }

    private RecruitmentStage composeBranch(RecruitmentStageDataRequest request, String oid) {
        Company company = companyRepository.findById(ServletRequestAWS.getCompanyId())
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_COMPANY_WITH_ID.getMsg()));

        RecruitmentStage recruitmentStage = new RecruitmentStage();
        if (oid != null) {
            recruitmentStage = recruitmentStageOrgRepository.findFirstByActive(oid)
                    .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_RECRUITMENT_STAGE_WITH_ID_FOUND.getMsg()));
            recruitmentStage.setUpdatedAt(LocalDateTime.now());
        } else {
            recruitmentStage.setStatus(StatusData.ACTIVE);
            recruitmentStage.setCreatedAt(LocalDateTime.now());
        }

        recruitmentStage.setCompany(companyMapper.toAttachCompany(company));

        recruitmentStageMapper.toWriteRecruitmentStage(recruitmentStage, request);
        return recruitmentStageOrgRepository.save(recruitmentStage);
    }

    private RecruitmentStageDataRequest getRecruitmentStageRequest(Map<Object, Object> request) {
        ObjectMapper mapper = new ObjectMapper();
        RecruitmentStageDataRequest dataRequest = mapper.convertValue(request, RecruitmentStageDataRequest.class);
        var hasAnyError_dataRequest = validator.validate(dataRequest);
        if (!hasAnyError_dataRequest.isEmpty()) throw new ConstraintViolationException(hasAnyError_dataRequest);

        return dataRequest;
    }
}
