package com.jobseeker.hrms.organization.service.basic.branch;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.branch.BranchDataRequest;
import com.jobseeker.hrms.organization.service.baseContract.BaseBranch;
import com.jobseeker.hrms.organization.service.basic.branch.command.CreateBranch;
import com.jobseeker.hrms.organization.service.basic.branch.command.DeleteBranch;
import com.jobseeker.hrms.organization.service.basic.branch.command.UpdateBranch;
import com.jobseeker.hrms.organization.service.basic.branch.query.GetBranch;
import com.jobseeker.hrms.organization.service.basic.branch.query.GetBranches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class BranchService<T> extends BaseBranch<T> {

	//<editor-fold desc="getBranch">
	@Autowired
	@Qualifier("getBranch")
	private GetBranch getBranch;

	@Override
	public T getBranch(String oid) {
		return (T) getBranch.execute(oid);
	}
	//</editor-fold>

	//<editor-fold desc="getBranches">
	@Autowired
	@Qualifier("getBranches")
	private GetBranches getBranches;

	@Override
	public Page<T> getBranches(Map<Object, Object> param) {
		return (Page<T>) getBranches.execute(param);
	}
	//</editor-fold>

	//<editor-fold desc="createBranch">
	@Autowired
	@Qualifier("createBranch")
	private CreateBranch createBranch;

	@Override
	public T createBranch(Map<Object, Object> param) {
		return (T) createBranch.execute(param);
	}
	//</editor-fold>

	//<editor-fold desc="updateBranch">
	@Autowired
	@Qualifier("updateBranch")
	private UpdateBranch updateBranch;

	@Override
	public T updateBranch(Map<Object, Object> request, String oid) {
		BranchDataRequest dataRequest = RequestHandler.remapToData(request, BranchDataRequest.class);
		return (T) updateBranch.execute(dataRequest, oid);
	}
	//</editor-fold>

	//<editor-fold desc="deleteBranch">
	@Autowired
	@Qualifier("deleteBranch")
	private DeleteBranch deleteBranch;

	@Override
	public String deleteBranch(String oid) {
		return deleteBranch.execute(oid);
	}
	//</editor-fold>
}
