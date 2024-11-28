package com.jobseeker.hrms.organization.interfaces;

import org.jobseeker.data.PaginationParam;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface IOfferingLetterHandler<T> {
	T getOfferingLetter();

	T updateOfferingLetter(Map<Object, Object> request) throws Exception;
}