package com.jobseeker.hrms.organization.interfaces;


import org.springframework.data.domain.Page;

import java.util.Map;

public interface IBranchHandler<T> {
	Page<T> getBranches(Map<Object, Object> param);

	T getBranch(String oid);

	T createBranch(Map<Object, Object> request) throws Exception;

	T updateBranch(Map<Object, Object> request, String oid) throws Exception;

	String deleteBranch(String oid) throws Exception;

}
