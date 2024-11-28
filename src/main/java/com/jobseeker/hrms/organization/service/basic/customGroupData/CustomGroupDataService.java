package com.jobseeker.hrms.organization.service.basic.customGroupData;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.customData.CustomDataParams;
import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationParams;
import com.jobseeker.hrms.organization.data.basic.customData.CustomGroupResponse;
import com.jobseeker.hrms.organization.mapper.basic.ICustomGroupDataMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.customGroup.CustomGroupDataOrgRepository;
import com.jobseeker.hrms.organization.service.BasePaginationService;
import com.jobseeker.hrms.organization.service.baseContract.BaseCustomGroupData;
import com.jobseeker.hrms.organization.service.basic.customData.query.GetCustomData;
import com.jobseeker.hrms.organization.service.basic.customGroupData.query.GetCustomGroupData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class CustomGroupDataService<T> extends BaseCustomGroupData<T> {

	//<editor-fold desc="getCustomGroupData">
	@Autowired
	@Qualifier("getCustomGroupData")
	private GetCustomGroupData getCustomGroupData;

	@Override
	public Page<T> getPaginatedCustomGroupData(Map<Object, Object> request) {
		CustomDataParams dataParams = RequestHandler.remapToData(request, CustomDataParams.class);
		return (Page<T>) getCustomGroupData.execute(dataParams);
	}
	//</editor-fold>
}
