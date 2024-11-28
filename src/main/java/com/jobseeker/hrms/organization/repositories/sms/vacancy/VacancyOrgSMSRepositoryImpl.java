package com.jobseeker.hrms.organization.repositories.sms.vacancy;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.lws.workArea.WorkAreaActiveVacancyLWSResponse;
import com.jobseeker.hrms.organization.mapper.basic.IFilterMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.MongoExpression;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ComparisonOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class VacancyOrgSMSRepositoryImpl implements VacancyOrgSMSRepositoryAdd {

    @Autowired
    @Qualifier("vacancyMongoTemplate")
    private MongoTemplate vacancyMongoTemplate;

    private final IFilterMapper filterMapper;

    @Override
    public List<WorkAreaActiveVacancyLWSResponse> findWorkAreaActiveVacancy() {
        LocalDateTime now = LocalDateTime.now();
        Criteria expiredCriteria = Criteria.where("expired_date").gte(now);
        MongoExpression expression =
                ComparisonOperators.Gt.valueOf("$number_of_employee_needed").greaterThan("$hire_count");
        Criteria exprCriteria = Criteria.expr(expression);
        Criteria matchcriteria = new Criteria().andOperator(
                Criteria.where("flag").is(1),
                Criteria.where("company._id").is(new ObjectId(ServletRequestAWS.getCompanyId())),
                Criteria.where("deleted_at").isNull(),
                expiredCriteria,
                exprCriteria
        );
        AggregationOperation matchOperation = Aggregation.match(matchcriteria);

        // Unwind stage
        AggregationOperation unwindOperation = Aggregation.unwind("area_placement_rank_list");

        AggregationOperation workAreaId = Aggregation.addFields()
                .addField("oid").withValue(
                        "$area_placement_rank_list.work_placement.work_area._id"
                ).build();
        AggregationOperation workAreaName = Aggregation.addFields()
                .addField("name").withValue(
                        "$area_placement_rank_list.work_placement.work_area.name"
                ).build();

        // Group stage
        AggregationOperation groupOperation = Aggregation.group("oid")
                .first("area_placement_rank_list.work_placement.work_area.name").as("name")
                .count().as("count");

        // Build the aggregation pipeline
        Aggregation aggregation = Aggregation.newAggregation(
                matchOperation,
                unwindOperation,
                workAreaId,
                workAreaName,
                groupOperation
        );

        // Execute the aggregation query
        AggregationResults<WorkAreaActiveVacancyLWSResponse> results = vacancyMongoTemplate.aggregate(aggregation, "vacancies", WorkAreaActiveVacancyLWSResponse.class);

        // Return the results as a list
        return results.getMappedResults();
    }

}
