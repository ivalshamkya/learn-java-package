package com.jobseeker.hrms.organization.service.basic.approval.command;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("deleteApproval")
public class DeleteApproval {

    public String execute(String oid) {
        return null;
    }
}
