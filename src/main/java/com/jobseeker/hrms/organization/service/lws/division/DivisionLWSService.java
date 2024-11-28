package com.jobseeker.hrms.organization.service.lws.division;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.lws.department.DepartmentLWSDataRequest;
import com.jobseeker.hrms.organization.data.lws.division.DivisionLWSDataRequest;
import com.jobseeker.hrms.organization.data.lws.division.DivisionLWSDataResponse;
import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.interfaces.IDivisionHandler;
import com.jobseeker.hrms.organization.mapper.basic.ICompanyMapper;
import com.jobseeker.hrms.organization.mapper.lws.IDivisionLWSMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.company.CompanyOrgRepository;
import com.jobseeker.hrms.organization.repositories.lws.organization.division.DivisionLWSRepository;
import com.jobseeker.hrms.organization.service.baseContract.BaseDivision;
import com.jobseeker.hrms.organization.service.lws.department.command.LawsonCreateDepartmentService;
import com.jobseeker.hrms.organization.service.lws.department.command.LawsonDeleteDepartmentService;
import com.jobseeker.hrms.organization.service.lws.department.command.LawsonUpdateDepartmentService;
import com.jobseeker.hrms.organization.service.lws.department.query.LawsonGetDepartmentService;
import com.jobseeker.hrms.organization.service.lws.department.query.LawsonGetDepartmentsService;
import com.jobseeker.hrms.organization.service.lws.division.command.LawsonCreateDivision;
import com.jobseeker.hrms.organization.service.lws.division.command.LawsonDeleteDivision;
import com.jobseeker.hrms.organization.service.lws.division.command.LawsonUpdateDivision;
import com.jobseeker.hrms.organization.service.lws.division.query.LawsonGetDivision;
import com.jobseeker.hrms.organization.service.lws.division.query.LawsonGetDivisions;
import lombok.RequiredArgsConstructor;
import org.jobseeker.data.PaginationParam;
import org.jobseeker.enums.general.MessageResponse;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Company;
import org.jobseeker.organization.lawson.DivisionLawson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@SuppressWarnings("unchecked")
public class DivisionLWSService<T> extends BaseDivision<T> {

	//<editor-fold desc="lawsonGetDivisions">
	@Autowired
	@Qualifier("lawsonGetDivisions")
	private LawsonGetDivisions lawsonGetDivisions;

	@Override
	public Page<T> getDivisions(Map<Object, Object> param) {
		PaginationLWSParams paginationParam = RequestHandler.remapToData(param, PaginationLWSParams.class);
		return (Page<T>) lawsonGetDivisions.execute(paginationParam);
	}
	//</editor-fold>

	//<editor-fold desc="lawsonGetDivision">
	@Autowired
	@Qualifier("lawsonGetDivision")
	private LawsonGetDivision getDivisionService;

	@Override
	public T getDivision(String oid) {
		return (T) getDivisionService.execute(oid);
	}
	//</editor-fold>

	//<editor-fold desc="lawsonDeleteDivision">
	@Autowired
	@Qualifier("lawsonDeleteDivision")
	private LawsonDeleteDivision deleteDivisionService;

	@Override
	public String deleteDivision(String oid) {
		return deleteDivisionService.execute(oid);
	}
	//</editor-fold>


	//<editor-fold desc="lawsonUpdateDivision">
	@Autowired
	@Qualifier("LawsonUpdateDivision")
	private LawsonUpdateDivision updateDivisionService;

	@Override
	public T updateDivision(Map<Object, Object> request, String oid) throws Exception {
		DivisionLWSDataRequest dataRequest = RequestHandler.remapToData(request, DivisionLWSDataRequest.class);
		return (T) updateDivisionService.execute(dataRequest, oid);
	}
	//</editor-fold>


	//<editor-fold desc="lawsonCreateDivision">
	@Autowired
	@Qualifier("LawsonCreateDivision")
	private LawsonCreateDivision lawsonCreateDivision;

	@Override
	public T createDivision(Map<Object, Object> request) throws Exception {
		DivisionLWSDataRequest dataRequest = RequestHandler.remapToData(request, DivisionLWSDataRequest.class);
		return (T) lawsonCreateDivision.execute(dataRequest);
	}
	//</editor-fold>
}
