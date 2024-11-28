package com.jobseeker.hrms.organization.service.basic.customGroupData.query;

import com.jobseeker.hrms.organization.data.basic.customData.CustomDataResponse;
import com.jobseeker.hrms.organization.data.basic.customData.CustomGroupResponse;
import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationParams;
import com.jobseeker.hrms.organization.mapper.basic.ICustomGroupDataMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.customGroup.CustomGroupDataOrgRepository;
import com.jobseeker.hrms.organization.service.BasePaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("GetCustomGroupData")
public class GetCustomGroupData extends BasePaginationService<CustomGroupResponse> {

    private final CustomGroupDataOrgRepository customGroupDataOrgRepository;
    private final ICustomGroupDataMapper customGroupDataMapper;

    public Page<CustomGroupResponse> execute(PaginationParams params) {
        return paginate(params,
                pageable -> customGroupDataOrgRepository.findByParamsPaginated(params, pageable)
                        .map(customGroupDataMapper::toResponse));
    }
}
