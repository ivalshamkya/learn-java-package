package com.jobseeker.hrms.organization.service.basic.importTemplate;

import com.jobseeker.hrms.organization.service.baseContract.BaseEmailTemplate;
import com.jobseeker.hrms.organization.service.baseContract.BaseImportTemplate;
import com.jobseeker.hrms.organization.service.basic.importTemplate.query.GetImportTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class ImportTemplateService<T> extends BaseImportTemplate<T> {

	//<editor-fold desc="getImportTemplates">
	@Autowired
	@Qualifier("getImportTemplate")
	private GetImportTemplate getImportTemplate;

	@Override
	public T getImportTemplate(String type) {
		return (T) getImportTemplate.execute(type);
	}
	//</editor-fold>
}
