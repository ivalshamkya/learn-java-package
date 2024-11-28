package com.jobseeker.hrms.organization.service.lws.filter;

import com.jobseeker.hrms.organization.service.baseContract.BaseFilter;
import com.jobseeker.hrms.organization.service.lws.filter.command.GetWorkAreaHasActiveVacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unchecked")
public class LawsonFilterService<T> extends BaseFilter<T> {


    //<editor-fold desc="lawsonGetWorkAreaHasActiveVacancy">
    @Autowired
    @Qualifier("lawsonGetWorkAreaHasActiveVacancy")
    private GetWorkAreaHasActiveVacancy getWorkAreaHasActiveVacancy;

    @Override
    public T getWorkAreaHasActiveVacancy() {
        return (T) getWorkAreaHasActiveVacancy.execute();
    }
    //</editor-fold>

}
