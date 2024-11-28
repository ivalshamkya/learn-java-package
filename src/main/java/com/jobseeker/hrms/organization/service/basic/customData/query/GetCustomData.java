package com.jobseeker.hrms.organization.service.basic.customData.query;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.customData.CustomDataParams;
import com.jobseeker.hrms.organization.data.basic.customData.CustomDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.ICustomDataMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.customData.CustomDataOrgRepository;
import com.jobseeker.hrms.organization.service.BasePaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("GetCustomData")
public class GetCustomData extends BasePaginationService<CustomDataResponse> {

    private final CustomDataOrgRepository customDataOrgRepository;
    private final ICustomDataMapper customDataMapper;

    public Page<CustomDataResponse> execute(CustomDataParams params) {
        String companyUrl = ServletRequestAWS.getOrigin();
        return paginate(params,
                pageable -> customDataOrgRepository.findByParamsPaginated(params, companyUrl, pageable)
                        .map(customDataMapper::toResponse));
    }
}
