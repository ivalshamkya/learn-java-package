package com.jobseeker.hrms.organization.repositories.basic.vacancy;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.filter.ActiveVacancy;
import com.jobseeker.hrms.organization.data.basic.filter.response.CityActiveVacancyResponse;
import com.jobseeker.hrms.organization.data.basic.filter.response.DepartmentActiveVacancyResponse;
import com.jobseeker.hrms.organization.mapper.basic.IFilterMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.jobseeker.vacancy.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.MongoExpression;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class VacancyOrgRepositoryImpl implements VacancyOrgRepositoryAdd {

    @Autowired
    @Qualifier("vacancyMongoTemplate")
    private MongoTemplate vacancyMongoTemplate;

    private final IFilterMapper filterMapper;

    @Override
    public List<DepartmentActiveVacancyResponse> findDepartmentActiveVacancy() {
        return queryDepartmentActiveVacancy(Vacancy.class)
                .stream().map(filterMapper::toDepartmentActiveVacancy)
                .toList();
    }

    @Override
    public List<CityActiveVacancyResponse> findCityActiveVacancy() {
        return queryCityActiveVacancy(Vacancy.class)
                .stream().map(filterMapper::toCityActiveVacancy)
                .toList();
    }

    public <T> List<ActiveVacancy> queryCityActiveVacancy(
            Class<T> entity
    ) {
        String companyId = ServletRequestAWS.getCompanyId();
        LocalDateTime now = LocalDateTime.now();

        Criteria expiredCriteria = new Criteria().orOperator(
                Criteria.where("expired_date").gte(now),
                Criteria.where("expired_date").exists(false)
        );

        MongoExpression expression =
                ComparisonOperators.Gt.valueOf("$number_of_employee_needed").greaterThan("$hire_count");
//                ComparisonOperators.Ne.valueOf("$number_of_employee_needed").notEqualTo("$hire_count");

        Criteria exprCriteria = Criteria.expr(expression);

        Criteria matchcriteria = new Criteria().andOperator(
                Criteria.where("flag").is(1),
                Criteria.where("company._id").is(new ObjectId(companyId)),
                Criteria.where("deleted_at").isNull(),
                expiredCriteria,
                exprCriteria
        );

        AggregationOperation matchOperation = Aggregation.match(matchcriteria);
        AggregationOperation groupOperation = Aggregation.group("city._id")
                .count().as("total_vacancies")
                .first("city").as("city");

        TypedAggregation<T> typedAggregation = Aggregation.newAggregation(entity, matchOperation, groupOperation);

        AggregationResults<ActiveVacancy> results = vacancyMongoTemplate.aggregate(typedAggregation, entity, ActiveVacancy.class);

        return extractContent(results);
    }

    public <T> List<ActiveVacancy> queryDepartmentActiveVacancy(
            Class<T> entity
    ) {
        String companyId = ServletRequestAWS.getCompanyId();
        LocalDateTime now = LocalDateTime.now();

        Criteria expiredCriteria = new Criteria().orOperator(
                Criteria.where("expired_date").gte(now),
//                Criteria.where("expired_date").isNull()
                Criteria.where("expired_date").exists(false)
        );

        MongoExpression expression =
//                ComparisonOperators.Gt.valueOf("$number_of_employee_needed").greaterThan("$hire_count");
                ComparisonOperators.Ne.valueOf("$number_of_employee_needed").notEqualTo("$hire_count");

        Criteria exprCriteria = Criteria.expr(expression);

        Criteria matchcriteria = new Criteria().andOperator(
                Criteria.where("flag").is(1),
                Criteria.where("company._id").is(new ObjectId(companyId)),
                Criteria.where("deleted_at").exists(false),
                expiredCriteria,
                exprCriteria
        );

        AggregationOperation matchOperation = Aggregation.match(matchcriteria);
        AggregationOperation groupOperation = Aggregation.group("department._id")
                .count().as("total_vacancies")
                .first("department").as("department");

        TypedAggregation<T> typedAggregation = Aggregation.newAggregation(entity, matchOperation, groupOperation);

        AggregationResults<ActiveVacancy> results = vacancyMongoTemplate.aggregate(typedAggregation, "vacancies", ActiveVacancy.class);

        return extractContent(results);

    }

    private <T> List<T> extractContent(AggregationResults<T> results) {
        return new ArrayList<>(results.getMappedResults());
    }
}
