package com.jobseeker.hrms.candidate.services.basic.command;

import com.jobseeker.hrms.candidate.config.ServletRequestAWS;
import com.jobseeker.hrms.candidate.services.general.SendMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("sendReminderCompleteProfile")
public class SendReminderCompleteProfile {

    private final SendMailService mailService;

    public void execute(String candidateId) {
        mailService.SendMail(candidateId, ServletRequestAWS.getCompanyId(), "COMPLETE_PROFILE");
    }
}
