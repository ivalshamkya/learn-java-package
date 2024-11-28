package com.jobseeker.hrms.organization.service.baseContract;

import com.jobseeker.hrms.organization.interfaces.IDocumentRequestHandler;
import org.springframework.data.domain.Page;

import java.util.Map;

public abstract class BaseDocumentRequest<T> implements IDocumentRequestHandler<T> {

	@Override
	public Page<T> getDocumentRequests(Map<Object, Object> param) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T getDocumentRequest(String oid) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T createDocumentRequest(Map<Object, Object> request) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public T updateDocumentRequest(Map<Object, Object> request, String oid) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String deleteDocumentRequest(String oid) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
