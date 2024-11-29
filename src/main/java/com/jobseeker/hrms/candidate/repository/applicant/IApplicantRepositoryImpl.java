package com.jobseeker.hrms.candidate.repository.applicant;

import com.jobseeker.hrms.candidate.config.ServletRequestAWS;
import com.jobseeker.hrms.candidate.mappers.ICandidateMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.jobseeker.candidate.Candidate;
import org.jobseeker.embedded.candidate.CandidateDataEmbed;
import org.jobseeker.vacancy.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IApplicantRepositoryImpl implements IApplicantRepositoryAdd {

    @Autowired
    @Qualifier("vacancyMongoTemplate")
    private MongoTemplate mongoTemplate;

    private final ICandidateMapper candidateMapper;

    @Override
    public void updateCandidate(Candidate candidate) {
        Criteria criteria = new Criteria().andOperator(
                Criteria.where("candidate._id").is(new ObjectId(candidate.get_id())),
                Criteria.where("company._id").is(ServletRequestAWS.getCompanyId())
        );
        CandidateDataEmbed candidateDataEmbed = candidateMapper.toCandidateDataEmbed(candidate);
        Update update = new Update()
                .set("candidate", candidateDataEmbed);

        Query query = Query.query(criteria);

        mongoTemplate.updateMulti(query, update, Applicant.class);
    }
}
