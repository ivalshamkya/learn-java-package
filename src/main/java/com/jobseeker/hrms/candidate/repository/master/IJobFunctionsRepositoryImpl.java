package com.jobseeker.hrms.candidate.repository.master;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.jobseeker.master.JobFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
@SuppressWarnings("SpringQualifierCopyableLombok")
public class IJobFunctionsRepositoryImpl implements IJobFunctionsRepositoryExtend {

    @Autowired
    @Qualifier("masterMongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public List<String> findJobFunctionRelatedToCategory(String categoryJobFunctionId) {
        try {
            Criteria criteria = Criteria.where("category._id").is(new ObjectId(categoryJobFunctionId));
            Query query = Query.query(criteria);

            List<JobFunction> jobFunctions = mongoTemplate.find(query, JobFunction.class);
            return jobFunctions.stream()
                    .map(JobFunction::get_id)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return List.of();
        }
    }

}
