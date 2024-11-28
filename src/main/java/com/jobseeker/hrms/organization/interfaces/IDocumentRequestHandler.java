package com.jobseeker.hrms.organization.interfaces;

import org.jobseeker.data.PaginationParam;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IDocumentRequestHandler<T> {
	Page<T> getDocumentRequests(Map<Object, Object> param);

	T getDocumentRequest(String oid);

	T createDocumentRequest(Map<Object, Object> request) throws Exception;

	T updateDocumentRequest(Map<Object, Object> request, String oid) throws Exception;

	String deleteDocumentRequest(String oid) throws Exception;
}