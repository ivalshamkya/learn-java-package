package com.jobseeker.hrms.candidate.repository.applicant;

import org.jobseeker.vacancy.Applicant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IApplicantRepository extends MongoRepository<Applicant, String>, IApplicantRepositoryAdd {

    @Query("{'candidate._id': ?0}")
    Optional<List<Applicant>> findApplicantByCandidateId(String candidateId);

    void deleteAllByCandidate__id(String oid);
}
