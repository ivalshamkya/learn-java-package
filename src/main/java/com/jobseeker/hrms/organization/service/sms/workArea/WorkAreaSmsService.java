package com.jobseeker.hrms.organization.service.sms.workArea;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.service.baseContract.BaseWorkArea;
import com.jobseeker.hrms.organization.service.sms.workArea.query.SMSGetWorkAreas;
import org.jobseeker.data.PaginationParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class WorkAreaSmsService<T> extends BaseWorkArea<T> {

	//<editor-fold desc="getWorkAreas">
	@Autowired
	@Qualifier("SMSGetWorkAreas")
	private SMSGetWorkAreas getWorkAreas;

	@Override
	public Page<T> getWorkAreas(Map<Object, Object> param) {
		PaginationParam dataRequest = RequestHandler.remapToData(param, PaginationParam.class);
		return (Page<T>) getWorkAreas.execute(dataRequest);
	}
	//</editor-fold>
}
