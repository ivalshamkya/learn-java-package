package com.jobseeker.hrms.organization.repositories.sms.organization.form;

import com.jobseeker.hrms.organization.data.sms.form.FormDataResponse;
import com.mongodb.BasicDBObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class FormSmsRepositoryImpl implements FormSmsRepositoryExtend {
    @Autowired
    @Qualifier("organizationMongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public List<FormDataResponse> findByCode(String code){
        AggregationOperation match = Aggregation.match(Criteria.where("form_group.form.code").is(code));

        AggregationOperation sortItems = Aggregation.sort(Sort.by(Sort.Order.asc("sequence")));

        AggregationOperation group = Aggregation.group("form_group.name")
                .push(new BasicDBObject("_id", "$_id")
                        .append("name", "$name")
                        .append("desc", "$desc")
                        .append("value_type", "$value_type")
                        .append("sequence", "$sequence")
                        .append("group_sequence", "$form_group.sequence"))
                .as("items");

        AggregationOperation sortGroups = Aggregation.sort(Sort.by(Sort.Order.asc("items.group_sequence")));

        AggregationOperation project = Aggregation.project()
                .and("_id").as("group_name")
                .andExclude("_id")
                .and("items").as("items");

        Aggregation aggregation = Aggregation.newAggregation(match, sortItems, group, sortGroups, project);

        AggregationResults<FormDataResponse> results = mongoTemplate.aggregate(aggregation, "form_group_items", FormDataResponse.class);

        return results.getMappedResults();
    }
}