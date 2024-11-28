package com.jobseeker.hrms.organization.service.basic.customSource;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.customSource.CustomSourceDataResponse;
import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationParams;
import com.jobseeker.hrms.organization.mapper.basic.ICustomSourceMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.customSource.CustomSourceOrgRepository;
import com.jobseeker.hrms.organization.service.baseContract.BaseCustomSource;
import com.jobseeker.hrms.organization.service.basic.branch.query.GetBranches;
import com.jobseeker.hrms.organization.service.basic.customSource.query.GetCustomSources;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class CustomSourceService<T> extends BaseCustomSource<T> {

	//<editor-fold desc="getCustomSources">
	@Autowired
	@Qualifier("getCustomSources")
	private GetCustomSources getCustomSources;

	@Override
	public Page<T> getCustomSources(Map<Object, Object> param) {
		PaginationParams dataRequest = RequestHandler.remapToData(param, PaginationParams.class);
		return (Page<T>) getCustomSources.execute(dataRequest);
	}
	//</editor-fold>
}
