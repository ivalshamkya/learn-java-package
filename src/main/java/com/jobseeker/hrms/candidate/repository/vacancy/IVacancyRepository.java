package com.jobseeker.hrms.candidate.repository.vacancy;

import org.jobseeker.vacancy.Vacancy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVacancyRepository extends MongoRepository<Vacancy, String>, IVacancyRepositoryExtend {
}
