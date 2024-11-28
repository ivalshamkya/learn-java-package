package com.jobseeker.hrms.organization.mapper.basic.service;

import com.jobseeker.hrms.organization.enums.ErrorMessages;
import com.jobseeker.hrms.organization.mapper.basic.IDepartmentMapper;
import com.jobseeker.hrms.organization.mapper.basic.IJobLevelMapper;
import com.jobseeker.hrms.organization.mapper.basic.IJobTypeMapper;
import com.jobseeker.hrms.organization.repositories.basic.organization.department.DepartmentOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.jobLevel.JobLevelOrgOrgRepository;
import com.jobseeker.hrms.organization.repositories.basic.organization.jobType.JobTypeOrgOrgRepository;
import lombok.RequiredArgsConstructor;
import org.jobseeker.embedded.general.GeneralDataEmbed;
import org.jobseeker.organization.Department;
import org.jobseeker.organization.JobLevel;
import org.jobseeker.organization.JobType;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class VacancyTemplateMapperService {

	private final DepartmentOrgRepository departmentRepository;
	private final IDepartmentMapper departmentMapper;
	private final IJobTypeMapper jobTypeMapper;
	private final IJobLevelMapper jobLevelMapper;
	private final JobTypeOrgOrgRepository jobTypeOrgRepository;
	private final JobLevelOrgOrgRepository jobLevelOrgRepository;

	@Named("setDepartment")
	public GeneralDataEmbed setDepartment(String departmentId) {
		Department department = departmentRepository.findFirstByActive(departmentId)
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_DEPARTMENT_WITH_ID_FOUND.getMsg()));
		return departmentMapper.toAttachDepartment(department);
	}

	@Named("setJobType")
	public  GeneralDataEmbed setJobType(String jobTypeId) {
		JobType jobType = jobTypeOrgRepository.findFirstByActive(jobTypeId)
				.orElseThrow(() -> new NoSuchElementException(ErrorMessages.NO_JOB_TYPE_FOUND.getMsg()));
		return jobTypeMapper.toAttachJobType(jobType);
	}

	@Named("setJobLevel")
	public GeneralDataEmbed setJobLevel(String jobLevelId) {
		JobLevel jobLevel = jobLevelOrgRepository.findFirstByActive(jobLevelId)
				 .orElseThrow(()-> new NoSuchElementException(ErrorMessages.NO_JOB_LEVEL_FOUND.getMsg()));
		return jobLevelMapper.toAttachJobLevel(jobLevel);
	}
}
