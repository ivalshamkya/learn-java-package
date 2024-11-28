package com.jobseeker.hrms.organization.interfaces;


import java.util.List;

public interface IPkwtTemplateHandler<T> {
	List<T> getPkwtTemplates();

	T getPkwtTemplate(String oid);
}
