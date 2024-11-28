package com.jobseeker.hrms.organization.service.lws.filter.command;

import com.jobseeker.hrms.organization.data.lws.workArea.WorkAreaActiveVacancyLWSResponse;
import com.jobseeker.hrms.organization.repositories.lws.vacancy.VacancyOrgLWSRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Qualifier("lawsonGetWorkAreaHasActiveVacancy")
public class GetWorkAreaHasActiveVacancy {

    private final VacancyOrgLWSRepository vacancyRepository;

    public Object execute() {
        List<WorkAreaActiveVacancyLWSResponse> data = vacancyRepository.findWorkAreaActiveVacancy();
        Map<String, Object> result = new HashMap<>();
        result.put("content", data);
        return result;
    }
}
