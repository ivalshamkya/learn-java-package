package com.jobseeker.hrms.organization.service.lws.division.query;

import com.jobseeker.hrms.organization.data.lws.division.DivisionLWSDataResponse;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.lws.IDivisionLWSMapper;
import com.jobseeker.hrms.organization.repositories.lws.organization.division.DivisionLWSRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.data.PaginationParam;
import org.jobseeker.organization.lawson.DivisionLawson;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Qualifier("LawsonGetDivisions")
public class LawsonGetDivision {

    private final DivisionLWSRepository divisionLWSRepository;

    private final IDivisionLWSMapper divisionLWSMapper;

    public DivisionLWSDataResponse execute(String oid) {
        DivisionLawson divisionLawson = divisionLWSRepository.findFirstByActive(oid)
                .orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_DEPARTMENT_WITH_ID_FOUND.getMsg()));
        return divisionLWSMapper.toDivisionLWSDataResponse(divisionLawson);
    }
}
