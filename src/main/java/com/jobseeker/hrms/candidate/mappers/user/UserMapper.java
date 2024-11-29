package com.jobseeker.hrms.candidate.mappers.user;

import org.jobseeker.auth.Role;
import org.jobseeker.candidate.Candidate;
import org.jobseeker.embedded.auth.RoleDataEmbed;
import org.jobseeker.embedded.auth.UserAsCandidateDataEmbed;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    RoleDataEmbed toAttach(Role roles);

    @Mapping(source = "_id", target = "candidateId")
    @Mapping(source = "name", target = "candidateName")
    UserAsCandidateDataEmbed toAttachUserCandidate(Candidate employee);
}
