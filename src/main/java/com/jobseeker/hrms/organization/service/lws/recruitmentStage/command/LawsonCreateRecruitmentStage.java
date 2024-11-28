package com.jobseeker.hrms.organization.service.lws.recruitmentStage.command;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.recruitmentStage.RecruitmentStageDataResponse;
import com.jobseeker.hrms.organization.data.lws.recruitmentStage.RecruitmentStageDataLWSRequest;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.lws.IRecruitmentStageLWSMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.recruitmentStage.RecruitmentStageOrgRepository;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.RecruitmentStage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("createRecruitmentStage")
public class LawsonCreateRecruitmentStage {

    private final CompanyOrgRepository companyRepository;
    private final RecruitmentStageOrgRepository recruitmentStageOrgRepository;

    private final ICompanyMapper companyMapper;
    private final IRecruitmentStageLWSMapper recruitmentStageMapper;

    public RecruitmentStageDataResponse execute(RecruitmentStageDataLWSRequest request) {
        RecruitmentStage recruitmentStage = composeBranch(request, null);

        return recruitmentStageMapper.toRecruitmentStageDataResponse(recruitmentStage);
    }

    private RecruitmentStage composeBranch(RecruitmentStageDataLWSRequest request, String oid) {
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
}
