package com.jobseeker.hrms.organization.service.basic.filter;

import com.jobseeker.hrms.organization.service.baseContract.BaseFilter;
import com.jobseeker.hrms.organization.service.basic.filter.command.GetCityHasActiveVacancy;
import com.jobseeker.hrms.organization.service.basic.filter.command.GetDepartmentHasActiveVacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unchecked")
public class FilterService<T> extends BaseFilter<T> {

    //<editor-fold desc="getDepartmentHasActiveVacancy">
    @Autowired
    @Qualifier("getDepartmentHasActiveVacancy")
    private GetDepartmentHasActiveVacancy getDepartmentHasActiveVacancy;

    @Override
    public T getDepartmentHasActiveVacancy() {
        return (T) getDepartmentHasActiveVacancy.execute();
    }
    //</editor-fold>

    //<editor-fold desc="getCityHasActiveVacancy">
    @Autowired
    @Qualifier("getCityHasActiveVacancy")
    private GetCityHasActiveVacancy getCityHasActiveVacancy;

    @Override
    public T getCityHasActiveVacancy() {
        return (T) getCityHasActiveVacancy.execute();
    }
    //</editor-fold>

}
