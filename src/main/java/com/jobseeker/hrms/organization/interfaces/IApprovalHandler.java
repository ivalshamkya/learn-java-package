package com.jobseeker.hrms.organization.interfaces;

import com.jobseeker.hrms.organization.data.basic.approval.ApprovalDatatableDataRequest;
import org.jobseeker.data.PaginationParam;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IApprovalHandler<T> {
	Page<T> getApprovals(Map<Object, Object> param);

	T getApproval(String oid);

	Object createApproval(Map<Object, Object> request) throws Exception;

	T updateApproval(Map<Object, Object> request, String oid) throws Exception;

	String deleteApproval(String oid) throws Exception;
}