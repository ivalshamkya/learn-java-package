package com.jobseeker.hrms.organization.interfaces;

import org.springframework.data.domain.Page;

import java.util.Map;

public interface ISoftSkillHandler<T> {
    T createSoftSkill(Map<Object, Object> request) throws Exception;
    T updateSoftSkill(Map<Object, Object> request, String oid) throws Exception;
    Page<T> getSoftSkills(Map<Object, Object> request);
    T getSoftSkill(String oid);
    String deleteSoftSkill(String oid);
}
