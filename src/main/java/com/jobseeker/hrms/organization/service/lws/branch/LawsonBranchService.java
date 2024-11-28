package com.jobseeker.hrms.organization.service.lws.branch;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.lws.branch.LawsonBranchDataRequest;
import com.jobseeker.hrms.organization.service.baseContract.BaseBranch;
import com.jobseeker.hrms.organization.service.lws.branch.command.LawsonCreateBranch;
import com.jobseeker.hrms.organization.service.lws.branch.command.LawsonDeleteBranch;
import com.jobseeker.hrms.organization.service.lws.branch.command.LawsonUpdateBranch;
import com.jobseeker.hrms.organization.service.lws.branch.query.LawsonGetBranch;
import com.jobseeker.hrms.organization.service.lws.branch.query.LawsonGetBranches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class LawsonBranchService<T> extends BaseBranch<T> {

	//<editor-fold desc="getBranch">
	@Autowired
	@Qualifier("lawsonGetBranch")
	private LawsonGetBranch getBranch;

	@Override
	public T getBranch(String oid) {
		return (T) getBranch.execute(oid);
	}
	//</editor-fold>

	//<editor-fold desc="getBranches">
	@Autowired
	@Qualifier("lawsonGetBranches")
	private LawsonGetBranches getBranches;

	@Override
	public Page<T> getBranches(Map<Object, Object> param) {
		return (Page<T>) getBranches.execute(param);
	}
	//</editor-fold>

	//<editor-fold desc="createBranch">
	@Autowired
	@Qualifier("lawsonCreateBranch")
	private LawsonCreateBranch createBranch;

	@Override
	public T createBranch(Map<Object, Object> param) {
		return (T) createBranch.execute(param);
	}
	//</editor-fold>

	//<editor-fold desc="updateBranch">
	@Autowired
	@Qualifier("lawsonUpdateBranch")
	private LawsonUpdateBranch updateBranch;

	@Override
	public T updateBranch(Map<Object, Object> request, String oid) {
		LawsonBranchDataRequest dataRequest = RequestHandler.remapToData(request, LawsonBranchDataRequest.class);
		return (T) updateBranch.execute(dataRequest, oid);
	}
	//</editor-fold>

	//<editor-fold desc="deleteBranch">
	@Autowired
	@Qualifier("lawsonDeleteBranch")
	private LawsonDeleteBranch deleteBranch;

	@Override
	public String deleteBranch(String oid) {
		return deleteBranch.execute(oid);
	}
	//</editor-fold>
}
