package com.jobseeker.hrms.organization.interfaces;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface IPositionHandler<T> {
	Page<T> getPositions(Map<Object, Object> param);

	T getPosition(String oid);

	T createPosition(Map<Object, Object> request) throws Exception;

	T updatePosition(Map<Object, Object> request, String oid) throws Exception;

	String deletePosition(String oid) throws Exception;
}