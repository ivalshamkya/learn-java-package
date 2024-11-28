package com.jobseeker.hrms.organization.service.basic.customData;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.customData.CustomDataParams;
import com.jobseeker.hrms.organization.service.baseContract.BaseCustomData;
import com.jobseeker.hrms.organization.service.basic.customData.query.GetCustomData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class CustomDataService<T> extends BaseCustomData<T> {

	//<editor-fold desc="getCustomData">
	@Autowired
	@Qualifier("getCustomData")
	private GetCustomData getCustomData;

	@Override
	public Page<T> getPaginatedCustomData(Map<Object, Object> request) {
		CustomDataParams dataParams = RequestHandler.remapToData(request, CustomDataParams.class);
		return (Page<T>) getCustomData.execute(dataParams);
	}
	//</editor-fold>

}
