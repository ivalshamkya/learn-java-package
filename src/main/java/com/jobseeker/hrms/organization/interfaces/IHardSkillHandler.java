package com.jobseeker.hrms.organization.interfaces;


import org.springframework.data.domain.Page;

import java.util.Map;

public interface IHardSkillHandler<T> {
    T createHardSkill(Map<Object, Object> request) throws Exception;
    T updateHardSkill(Map<Object, Object> request, String oid) throws Exception;
    Page<T> getHardSkills(Map<Object, Object> request);
    T getHardSkill(String oid);
    String deleteHardSkill(String oid);
}
