package com.jobseeker.hrms.organization.config.appHandler;

import com.jobseeker.hrms.organization.service.baseContract.*;
import com.jobseeker.hrms.organization.service.basic.approval.ApprovalService;
import com.jobseeker.hrms.organization.service.basic.branch.BranchService;
import com.jobseeker.hrms.organization.service.basic.company.CompanyOrgService;
import com.jobseeker.hrms.organization.service.basic.customData.CustomDataService;
import com.jobseeker.hrms.organization.service.basic.customGroupData.CustomGroupDataService;
import com.jobseeker.hrms.organization.service.basic.customSource.CustomSourceService;
import com.jobseeker.hrms.organization.service.basic.department.DepartmentBasicService;
import com.jobseeker.hrms.organization.service.basic.documentRequest.DocumentRequestService;
import com.jobseeker.hrms.organization.service.basic.emailTemplate.EmailTemplateService;
import com.jobseeker.hrms.organization.service.basic.filter.FilterService;
import com.jobseeker.hrms.organization.service.basic.hardSkill.HardSkillService;
import com.jobseeker.hrms.organization.service.basic.houseStatus.HouseStatusService;
import com.jobseeker.hrms.organization.service.basic.importTemplate.ImportTemplateService;
import com.jobseeker.hrms.organization.service.basic.jobLevel.JobLevelService;
import com.jobseeker.hrms.organization.service.basic.jobType.JobTypeService;
import com.jobseeker.hrms.organization.service.basic.offeringLetter.OfferingLetterService;
import com.jobseeker.hrms.organization.service.basic.position.PositionBasicService;
import com.jobseeker.hrms.organization.service.basic.reasonOrganization.ReasonOrganizationService;
import com.jobseeker.hrms.organization.service.basic.recruitmentStage.RecruitmentStageService;
import com.jobseeker.hrms.organization.service.basic.religionOrganization.ReligionOrganizationService;
import com.jobseeker.hrms.organization.service.basic.softSkill.SoftSkillService;
import com.jobseeker.hrms.organization.service.basic.workplacement.WorkplacementService;
import com.jobseeker.hrms.organization.service.lws.branch.LawsonBranchService;
import com.jobseeker.hrms.organization.service.lws.businessUnit.BusinessUnitLawsonService;
import com.jobseeker.hrms.organization.service.lws.candidateLanguage.CandidateLanguageLawsonService;
import com.jobseeker.hrms.organization.service.lws.department.DepartmentLawsonService;
import com.jobseeker.hrms.organization.service.lws.division.DivisionLWSService;
import com.jobseeker.hrms.organization.service.lws.filter.LawsonFilterService;
import com.jobseeker.hrms.organization.service.lws.hardSkill.LawsonHardSkillService;
import com.jobseeker.hrms.organization.service.lws.jobLevel.JobLevelLWSService;
import com.jobseeker.hrms.organization.service.lws.jobType.JobTypeLWSService;
import com.jobseeker.hrms.organization.service.lws.position.PositionLawsonService;
import com.jobseeker.hrms.organization.service.lws.recruitmentStage.RecruitmentStageLWSService;
import com.jobseeker.hrms.organization.service.lws.satDisability.SATDisabilityLawsonService;
import com.jobseeker.hrms.organization.service.lws.satMaritalStatus.SATMaritalStatusLawsonService;
import com.jobseeker.hrms.organization.service.lws.satNationality.SATNationalityLawsonService;
import com.jobseeker.hrms.organization.service.lws.softSkill.LawsonSoftSkillService;
import com.jobseeker.hrms.organization.service.lws.workArea.WorkAreaLawsonService;
import com.jobseeker.hrms.organization.service.lws.workplacement.WorkplacementLawsonService;
import com.jobseeker.hrms.organization.service.sms.branch.BranchSMSService;
import com.jobseeker.hrms.organization.service.sms.form.FormSMSService;
import com.jobseeker.hrms.organization.service.sms.importLog.ImportLogSMSService;
import com.jobseeker.hrms.organization.service.sms.pkwtTemplate.PkwtTemplateSMSService;
import com.jobseeker.hrms.organization.service.sms.vacancyTemplate.SMSVacancyTemplateService;
import com.jobseeker.hrms.organization.service.sms.workArea.WorkAreaSmsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = "com.jobseeker.hrms.organization")
public class AppRegistryHandler {

	@Bean(name = "branchHandlerMap")
	public Map<String, BaseBranch<?>> branchHandlerMapConfig(
			LawsonBranchService<?> branchLawsonService,
			BranchService<?> branchService,
			BranchSMSService<?> branchSMSService
	) {
		Map<String, BaseBranch<?>> branchMap = new HashMap<>();
			branchMap.put("hrms-basic", branchService);
			branchMap.put("careersite", branchService);
			branchMap.put("jobseeker.life", branchService);

			branchMap.put("hrms-sms", branchSMSService);
			branchMap.put("careersite-sms", branchSMSService);

			branchMap.put("hrms-lawson", branchLawsonService);
			branchMap.put("careersite-lawson", branchLawsonService);

		return branchMap;
	}

	@Bean(name = "vacancyTemplateHandlerMap")
	public Map<String, BaseVacancyTemplate<?,?>> vacancyTemplateHandlerMapConfig(
			SMSVacancyTemplateService<?, ?> vacancyTemplateSMSService
	) {
		Map<String, BaseVacancyTemplate<?,?>> vacancyTemplateMap = new HashMap<>();

		vacancyTemplateMap.put("hrms-sms", vacancyTemplateSMSService);
		vacancyTemplateMap.put("careersite-sms", vacancyTemplateSMSService);

		vacancyTemplateMap.put("hrms-lawson", vacancyTemplateSMSService);
		vacancyTemplateMap.put("careersite-lawson", vacancyTemplateSMSService);

		return vacancyTemplateMap;
	}

	@Bean(name = "departmentHandlerMap")
	public Map<String, BaseDepartment<?>> departmentHandlerMapConfig(
			DepartmentBasicService<?> departmentBasicService,
			DepartmentLawsonService<?> departmentLawsonService
	) {
		Map<String, BaseDepartment<?>> departmentMap = new HashMap<>();

		departmentMap.put("hrms-basic", departmentBasicService);
		departmentMap.put("careersite", departmentBasicService);
		departmentMap.put("jobseeker.life", departmentBasicService);

		departmentMap.put("hrms-sms", departmentBasicService);
		departmentMap.put("careersite-sms", departmentBasicService);

		departmentMap.put("hrms-lawson", departmentLawsonService);
		departmentMap.put("careersite-lawson", departmentLawsonService);

		return departmentMap;
	}

	@Bean(name = "jobLevelHandlerMap")
	public Map<String, BaseJobLevel<?>> jobLevelHandlerMapConfig(
			JobLevelService<?> jobLevelService,
			JobLevelLWSService<?> jobLevelLWSService
	) {
		Map<String, BaseJobLevel<?>> jobLevelMap = new HashMap<>();
		jobLevelMap.put("hrms-basic", jobLevelService);
		jobLevelMap.put("careersite", jobLevelService);

		jobLevelMap.put("hrms-sms", jobLevelService);
		jobLevelMap.put("careersite-sms", jobLevelService);

		jobLevelMap.put("hrms-lawson", jobLevelLWSService);
		jobLevelMap.put("careersite-lawson", jobLevelLWSService);

		return jobLevelMap;
	}
	
	// ----- Handler for Hard Skill Service ----- //
	@Bean(name = "hardSkillHandlerMap")
	public Map<String, BaseHardSkill<?>> hardSkillHandlerMapConfig(
		HardSkillService<?> hardSkillService,
		LawsonHardSkillService<?> lawsonHardSkillService
	) {
		Map<String, BaseHardSkill<?>> hardSkillMap = new HashMap<>();
		hardSkillMap.put("hrms-basic", hardSkillService);
		hardSkillMap.put("careersite", hardSkillService);
		
		hardSkillMap.put("hrms-sms", hardSkillService);
		hardSkillMap.put("careersite-sms", hardSkillService);
		
		hardSkillMap.put("hrms-lawson", lawsonHardSkillService);
		hardSkillMap.put("careersite-lawson", hardSkillService);
		
		return hardSkillMap;
	}
	// ----- End Handler for Soft Skill Service ----- //
	
	// ----- Handler for Hard Skill Service ----- //
	@Bean(name = "softSkillHandlerMap")
	public Map<String, BaseSoftSkill<?>> softSkillHandlerMapConfig(
		SoftSkillService<?> softSkillService,
		LawsonSoftSkillService<?> lawsonSoftSkillService
	) {
		Map<String, BaseSoftSkill<?>> softSkillMap = new HashMap<>();
		softSkillMap.put("hrms-basic", softSkillService);
		softSkillMap.put("careersite", softSkillService);
		
		softSkillMap.put("hrms-sms", softSkillService);
		softSkillMap.put("careersite-sms", softSkillService);
		
		softSkillMap.put("hrms-lawson", lawsonSoftSkillService);
		softSkillMap.put("careersite-lawson", softSkillService);
		
		return softSkillMap;
	}
	// ----- End Handler for Soft Skill Service ----- //

	@Bean(name = "jobTypeHandlerMap")
	public Map<String, BaseJobType<?>> jobTypeHandlerMapConfig(
			JobTypeService<?> jobTypeService,
			JobTypeLWSService<?> jobTypeLWSService
	) {
		Map<String, BaseJobType<?>> jobTypeMap = new HashMap<>();
		jobTypeMap.put("hrms-basic", jobTypeService);
		jobTypeMap.put("careersite", jobTypeService);

		jobTypeMap.put("hrms-sms", jobTypeService);
		jobTypeMap.put("careersite-sms", jobTypeService);

		jobTypeMap.put("hrms-lawson", jobTypeLWSService);
		jobTypeMap.put("careersite-lawson", jobTypeLWSService);

		return jobTypeMap;
	}

	@Bean(name = "positionHandlerMap")
	public Map<String, BasePosition<?>> positionHandlerMapConfig(
			PositionBasicService<?> positionService,
			PositionLawsonService<?> positionLWSService
	) {
		Map<String, BasePosition<?>> positionMap = new HashMap<>();
		positionMap.put("hrms-basic", positionService);
		positionMap.put("careersite", positionService);

		positionMap.put("hrms-sms", positionService);
		positionMap.put("careersite-sms", positionService);

		positionMap.put("hrms-lawson", positionLWSService);
		positionMap.put("careersite-lawson", positionLWSService);

		return positionMap;
	}

	@Bean(name = "recruitmentStageHandlerMap")
	public Map<String, BaseRecruitmentStage<?>> recruitmentStageHandlerMapConfig(
			RecruitmentStageService<?> recruitmentStageService,
			RecruitmentStageLWSService<?> recruitmentStageLWSService
	) {
		Map<String, BaseRecruitmentStage<?>> recruitmentStageMap = new HashMap<>();
		recruitmentStageMap.put("hrms-basic", recruitmentStageService);
		recruitmentStageMap.put("careersite", recruitmentStageService);

		recruitmentStageMap.put("hrms-sms", recruitmentStageService);
		recruitmentStageMap.put("careersite-sms", recruitmentStageService);

		recruitmentStageMap.put("hrms-lawson", recruitmentStageLWSService);
		recruitmentStageMap.put("careersite-lawson", recruitmentStageLWSService);

		return recruitmentStageMap;
	}

	@Bean(name = "workplacementHandlerMap")
	public Map<String, BaseWorkplacement<?>> workplacementHandlerMapConfig(
			WorkplacementService<?> workplacementService,
			WorkplacementLawsonService<?> workplacementLWSService
	) {
		Map<String, BaseWorkplacement<?>> workplacementMap = new HashMap<>();
		workplacementMap.put("hrms-basic", workplacementService);
		workplacementMap.put("careersite", workplacementService);

		workplacementMap.put("hrms-sms", workplacementService);
		workplacementMap.put("careersite-sms", workplacementService);

		workplacementMap.put("hrms-lawson", workplacementLWSService);
		workplacementMap.put("careersite-lawson", workplacementLWSService);

		return workplacementMap;
	}

	@Bean(name = "formHandlerMap")
	public Map<String, BaseForm<?>> formHandlerMapConfig(
			FormSMSService<?> formService
	) {
		Map<String, BaseForm<?>> formMap = new HashMap<>();
		formMap.put("hrms-sms", formService);

		return formMap;
	}

	@Bean(name = "pkwtTemplateHandlerMap")
	public Map<String, BasePkwtTemplate<?>> pkwtTemplateHandlerMapConfig(
			PkwtTemplateSMSService<?> pkwtTemplateService
	) {
		Map<String, BasePkwtTemplate<?>> pkwtTemplateMap = new HashMap<>();
		pkwtTemplateMap.put("hrms-sms", pkwtTemplateService);

		return pkwtTemplateMap;
	}

	@Bean(name = "workAreaHandlerMap")
	public Map<String, BaseWorkArea<?>> workAreaHandlerMapConfig(
			WorkAreaLawsonService<?> workAreaLWSService,
			WorkAreaSmsService<?> workAreaSmsService
	) {
		Map<String, BaseWorkArea<?>> workAreaMap = new HashMap<>();

		workAreaMap.put("hrms-lawson", workAreaLWSService);
		workAreaMap.put("careersite-lawson", workAreaLWSService);

		workAreaMap.put("hrms-sms", workAreaSmsService);
		workAreaMap.put("careersite-sms", workAreaSmsService);

		return workAreaMap;
	}

	@Bean(name = "businessUnitHandlerMap")
	public Map<String, BaseBusinessUnit<?>> businessUnitHandlerMapConfig(
			BusinessUnitLawsonService<?> businessUnitLWSService
	) {
		Map<String, BaseBusinessUnit<?>> businessUnitMap = new HashMap<>();

		businessUnitMap.put("hrms-lawson", businessUnitLWSService);
		businessUnitMap.put("careersite-lawson", businessUnitLWSService);

		return businessUnitMap;
	}

	@Bean(name = "customSourceHandlerMap")
	public Map<String, BaseCustomSource<?>> customSourceHandlerMapConfig(
			CustomSourceService<?> customSourceService
	) {
		Map<String, BaseCustomSource<?>> customSourceMap = new HashMap<>();

		customSourceMap.put("hrms-lawson", customSourceService);
		customSourceMap.put("careersite-lawson", customSourceService);

		return customSourceMap;
	}

	@Bean(name = "emailTemplateHandlerMap")
	public Map<String, BaseEmailTemplate<?>> emailTemplateHandlerMapConfig(
			EmailTemplateService<?> emailTemplateService
	) {
		Map<String, BaseEmailTemplate<?>> emailTemplateMap = new HashMap<>();
		emailTemplateMap.put("hrms-lawson", emailTemplateService);

		return emailTemplateMap;
	}

	@Bean(name = "importTemplateHandlerMap")
	public Map<String, BaseImportTemplate<?>> importTemplateHandlerMapConfig(
			ImportTemplateService<?> importTemplateService
	) {
		Map<String, BaseImportTemplate<?>> importTemplateMap = new HashMap<>();

		importTemplateMap.put("hrms-sms", importTemplateService);

		return importTemplateMap;
	}

	@Bean(name = "divisionHandlerMap")
	public Map<String, BaseDivision<?>> divisionHandlerMapConfig(
			DivisionLWSService<?> divisionLWSService
	) {
		Map<String, BaseDivision<?>> divisionMap = new HashMap<>();

		divisionMap.put("hrms-lawson", divisionLWSService);
		divisionMap.put("careersite-lawson", divisionLWSService);

		return divisionMap;
	}

	@Bean(name = "documentRequestHandlerMap")
	public Map<String, BaseDocumentRequest<?>> documentRequestHandlerMapConfig(
			DocumentRequestService documentRequestService
	) {
		Map<String, BaseDocumentRequest<?>> documentRequestMap = new HashMap<>();
		documentRequestMap.put("hrms-basic", documentRequestService);
		documentRequestMap.put("careersite", documentRequestService);

		documentRequestMap.put("hrms-sms", documentRequestService);
		documentRequestMap.put("careersite-sms", documentRequestService);

		documentRequestMap.put("hrms-lawson", documentRequestService);
		documentRequestMap.put("careersite-lawson", documentRequestService);


		return documentRequestMap;
	}

	@Bean(name = "offeringLetterHandlerMap")
	public Map<String, BaseOfferingLetter<?>> offeringLetterHandlerMapConfig(
			OfferingLetterService<?> offeringLetterService
	) {
		Map<String, BaseOfferingLetter<?>> offeringLetterMap = new HashMap<>();
		offeringLetterMap.put("hrms-basic", offeringLetterService);
		offeringLetterMap.put("careersite", offeringLetterService);

		offeringLetterMap.put("hrms-sms", offeringLetterService);
		offeringLetterMap.put("careersite-sms", offeringLetterService);

		offeringLetterMap.put("hrms-lawson", offeringLetterService);
		offeringLetterMap.put("careersite-lawson", offeringLetterService);

		return offeringLetterMap;
	}

	@Bean(name = "companyHandlerMap")
	public Map<String, BaseCompany<?>> companyHandlerMapConfig(
			CompanyOrgService<?> companyService
	) {
		Map<String, BaseCompany<?>> companyMap = new HashMap<>();
		companyMap.put("hrms-basic", companyService);
		companyMap.put("careersite", companyService);
		companyMap.put("jobseeker.life", companyService);

		companyMap.put("hrms-sms", companyService);
		companyMap.put("careersite-sms", companyService);

		companyMap.put("hrms-lawson", companyService);
		companyMap.put("careersite-lawson", companyService);

		return companyMap;
	}

	@Bean(name = "approvalHandlerMap")
	public Map<String, BaseApproval<?>> approvalHandlerMapConfig(
			ApprovalService<?> approvalService
	) {
		Map<String, BaseApproval<?>> approvalMap = new HashMap<>();
		approvalMap.put("hrms-basic", approvalService);
		approvalMap.put("careersite", approvalService);

		approvalMap.put("hrms-sms", approvalService);
		approvalMap.put("careersite-sms", approvalService);

		approvalMap.put("hrms-lawson", approvalService);
		approvalMap.put("careersite-lawson", approvalService);

		return approvalMap;
	}

	@Bean(name = "filterHandlerMap")
	public Map<String, BaseFilter<?>> filterHandlerMapConfig(
			FilterService<?> filterService,
			LawsonFilterService<?> lawsonFilterService
	) {
		Map<String, BaseFilter<?>> filterMap = new HashMap<>();
		filterMap.put("hrms-basic", filterService);
		filterMap.put("careersite", filterService);

		filterMap.put("hrms-sms", filterService);
		filterMap.put("careersite-sms", filterService);

		filterMap.put("hrms-lawson", filterService);
		filterMap.put("careersite-lawson", lawsonFilterService);

		return filterMap;
	}

	@Bean(name = "customDataHandlerMap")
	public Map<String, BaseCustomData<?>> customDataHandlerMapConfig(
			CustomDataService<?> customDataService
	) {
		Map<String, BaseCustomData<?>> customDataMap = new HashMap<>();
		customDataMap.put("hrms-basic", customDataService);
		customDataMap.put("careersite", customDataService);

		customDataMap.put("hrms-sms", customDataService);
		customDataMap.put("careersite-sms", customDataService);

		customDataMap.put("hrms-lawson", customDataService);
		customDataMap.put("careersite-lawson", customDataService);

		return customDataMap;
	}

	@Bean(name = "customGroupDataHandlerMap")
	public Map<String, BaseCustomGroupData<?>> customGroupDataHandlerMapConfig(
			CustomGroupDataService<?> customGroupDataService
	) {
		Map<String, BaseCustomGroupData<?>> customGroupDataMap = new HashMap<>();
		customGroupDataMap.put("hrms-basic", customGroupDataService);
		customGroupDataMap.put("careersite", customGroupDataService);

		customGroupDataMap.put("hrms-sms", customGroupDataService);
		customGroupDataMap.put("careersite-sms", customGroupDataService);

		customGroupDataMap.put("hrms-lawson", customGroupDataService);
		customGroupDataMap.put("careersite-lawson", customGroupDataService);

		return customGroupDataMap;
	}
	
	@Bean(name = "nationalityHandlerMap")
	public Map<String, BaseSATNationality<?>> satNationHandlerMapConfig(
		SATNationalityLawsonService<?> satNationalityLawsonService
	){
		Map<String, BaseSATNationality<?>> satNationalityMap = new HashMap<>();
		satNationalityMap.put("hrms-lawson", satNationalityLawsonService);
		satNationalityMap.put("careersite-lawson", satNationalityLawsonService);

		return satNationalityMap;
	}
	
	@Bean(name = "maritalStatusHandlerMap")
	public Map<String, BaseSATMaritalStatus<?>> satMaritalStatusHandlerMapConfig(
		SATMaritalStatusLawsonService<?> satMaritalStatusLawsonService
	) {
		Map<String, BaseSATMaritalStatus<?>> satMaritalStatus = new HashMap<>();
		satMaritalStatus.put("hrms-lawson", satMaritalStatusLawsonService);
		satMaritalStatus.put("careersite-lawson", satMaritalStatusLawsonService);

		return satMaritalStatus;
	}
	
	@Bean(name = "disabilityHandlerMap")
	public Map<String, BaseSATDisability<?>> satDisabilityMapConfig(
		SATDisabilityLawsonService<?> satDisabilityLawsonService
	) {
		Map<String, BaseSATDisability<?>> satDisability = new HashMap<>();
		satDisability.put("hrms-lawson", satDisabilityLawsonService);
		satDisability.put("careersite-lawson", satDisabilityLawsonService);
		return satDisability;
	}
	
	@Bean(name = "reasonOrganizationHandlerMap")
	public Map<String, BaseReasonOrganization<?>> reasonOrganizationMapConfig(
		ReasonOrganizationService<?> reasonOrganizationService
	) {
		Map<String, BaseReasonOrganization<?>> reasonOrganization = new HashMap<>();
		reasonOrganization.put("hrms-sms", reasonOrganizationService);
		return reasonOrganization;
	}
	
	@Bean(name = "religionOrganizationHandlerMap")
	public Map<String, BaseReligionOrganization<?>> religionOrganizationMapConfig(
		ReligionOrganizationService<?> religionOrganizationService
	) {
		Map<String, BaseReligionOrganization<?>> religionOrganization = new HashMap<>();
		religionOrganization.put("hrms-lawson", religionOrganizationService);
		religionOrganization.put("careersite-lawson", religionOrganizationService);
		return religionOrganization;
	}

	@Bean(name = "houseStatusHandlerMap")
	    public Map<String, BaseHouseStatus<?>> houseStatusMapConfig(
            HouseStatusService<?> houseStatusService
    ) {
        Map<String, BaseHouseStatus<?>> houseStatus = new HashMap<>();
        houseStatus.put("hrms-basic", houseStatusService);
		houseStatus.put("hrms-lawson", houseStatusService);
		houseStatus.put("careersite-lawson", houseStatusService);

		return houseStatus;
	}

    @Bean(name = "candidateLanguageHandlerMap")
	public Map<String, BaseCandidateLanguage<?>> candidateLanguageMapConfig(
            CandidateLanguageLawsonService<?> candidateLanguageLawsonService
    ) {
        Map<String, BaseCandidateLanguage<?>> candidateLanguage = new HashMap<>();
        candidateLanguage.put("hrms-lawson", candidateLanguageLawsonService);
        candidateLanguage.put("careersite-lawson", candidateLanguageLawsonService);

		return candidateLanguage;
	}

	@Bean(name = "importLogHandlerMap")
	public Map<String, BaseImportLog<?>> importLogMapConfig(
			ImportLogSMSService<?> importLogSMSService
	) {
		Map<String, BaseImportLog<?>> importLogMap = new HashMap<>();
		importLogMap.put("hrms-sms", importLogSMSService);

		return importLogMap;
	}
}
