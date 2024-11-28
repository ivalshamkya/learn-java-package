package com.jobseeker.hrms.organization.service.basic.approval;

import com.jobseeker.hrms.organization.config.appHandler.RequestHandler;
import com.jobseeker.hrms.organization.data.basic.approval.ApprovalDataRequest;
import com.jobseeker.hrms.organization.data.basic.approval.ApprovalDatatableDataRequest;
import com.jobseeker.hrms.organization.service.baseContract.BaseApproval;
import com.jobseeker.hrms.organization.service.basic.approval.command.CreateApproval;
import com.jobseeker.hrms.organization.service.basic.approval.command.DeleteApproval;
import com.jobseeker.hrms.organization.service.basic.approval.command.UpdateApproval;
import com.jobseeker.hrms.organization.service.basic.approval.query.GetApproval;
import com.jobseeker.hrms.organization.service.basic.approval.query.GetApprovals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class ApprovalService<T> extends BaseApproval<T> {

	//<editor-fold desc="getApproval">
	@Autowired
	@Qualifier("getApproval")
	private GetApproval getApproval;

	@Override
	public T getApproval(String oid) {
		return (T) getApproval.execute(oid);
	}
	//</editor-fold>

	//<editor-fold desc="getApprovals">
	@Autowired
	@Qualifier("getApprovals")
	private GetApprovals getApprovals;

	@Override
	public Page<T> getApprovals(Map<Object, Object> request) {
		ApprovalDatatableDataRequest dataRequest = RequestHandler.remapToData(request, ApprovalDatatableDataRequest.class);
		return (Page<T>) getApprovals.execute(dataRequest);
	}
	//</editor-fold>

	//<editor-fold desc="createApproval">
	@Autowired
	@Qualifier("createApproval")
	private CreateApproval createApproval;

	@Override
	public Object createApproval(Map<Object, Object> request) throws Exception {
		ApprovalDataRequest dataRequest = RequestHandler.remapToData(request, ApprovalDataRequest.class);
		return createApproval.execute(dataRequest);
	}
	//</editor-fold>

	//<editor-fold desc="updateApproval">
	@Autowired
	@Qualifier("updateApproval")
	private UpdateApproval updateApproval;

	@Override
	public T updateApproval(Map<Object, Object> request, String oid) {
		return (T) updateApproval.execute(request, oid);
	}
	//</editor-fold>

	//<editor-fold desc="deleteApproval">
	@Autowired
	@Qualifier("deleteApproval")
	private DeleteApproval deleteApproval;

	@Override
	public String deleteApproval(String oid) {
		return deleteApproval.execute(oid);
	}
	//</editor-fold>
}
