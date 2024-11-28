package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.data.basic.approval.ApprovalDatatableDataRequest;
import com.jobseeker.hrms.organization.interfaces.IApprovalHandler;
import com.jobseeker.hrms.organization.interfaces.ICompanyHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

public abstract class BaseApproval<T> implements IApprovalHandler<T> {

	@Override
	public Page<T> getApprovals(Map<Object, Object> param) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T getApproval(String oid) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Object createApproval(Map<Object, Object> request) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T updateApproval(Map<Object, Object> request, String oid) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String deleteApproval(String oid) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
