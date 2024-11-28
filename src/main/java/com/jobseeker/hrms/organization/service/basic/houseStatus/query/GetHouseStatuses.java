package com.jobseeker.hrms.organization.service.basic.houseStatus.query;

import com.jobseeker.hrms.organization.data.basic.houseStatus.HouseStatusDataResponse;
import com.jobseeker.hrms.organization.mapper.basic.IHouseStatusMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.houseStatus.HouseStatusOrgOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("getHouseStatuses")
public class GetHouseStatuses {
    private final HouseStatusOrgOrgRepository houseStatusOrgRepository;
    private final IHouseStatusMapper houseStatusMapper;

    public Page<HouseStatusDataResponse> execute(PaginationParam param) {
        return houseStatusOrgRepository.findByDataTables(param)
                .map(houseStatusMapper::toHouseStatusDataResponse);
    }
}
