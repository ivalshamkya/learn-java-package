package com.jobseeker.hrms.organization.service.basic.filter.command;

import com.jobseeker.hrms.organization.data.basic.filter.response.DepartmentActiveVacancyResponse;
import com.jobseeker.hrms.organization.repositories.basic.vacancy.VacancyOrgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Qualifier("getDepartmentHasActiveVacancy")
public class GetDepartmentHasActiveVacancy {

    private final VacancyOrgRepository vacancyRepository;

    public Object execute() {
        List<DepartmentActiveVacancyResponse> data = vacancyRepository.findDepartmentActiveVacancy();
        Map<String, Object> result = new HashMap<>();
        result.put("content", data);
        return result;
    }
}
