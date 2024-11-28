package com.jobseeker.hrms.organization.service.basic.houseStatus;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.service.baseContract.BaseHouseStatus;
import com.jobseeker.hrms.organization.service.basic.houseStatus.query.GetHouseStatuses;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class HouseStatusService<T> extends BaseHouseStatus<T> {
    //<editor-fold desc="getHouseStatuses">
    @Autowired
    @Qualifier("getHouseStatuses")
    private GetHouseStatuses getHouseStatuses;

    @Override
    public Page<T> getHouseStatuses(Map<Object, Object> request) {
        PaginationParam param = RequestHandler.remapToData(request, PaginationParam.class);
        return (Page<T>) getHouseStatuses.execute(param);
    }
    //</editor-fold>
}
