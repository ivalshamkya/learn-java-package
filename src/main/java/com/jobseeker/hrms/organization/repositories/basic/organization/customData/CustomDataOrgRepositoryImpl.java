package com.jobseeker.hrms.organization.repositories.basic.organization.customData;

import com.jobseeker.hrms.organization.data.basic.customData.CustomDataParams;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.CustomData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class CustomDataOrgRepositoryImpl implements CustomDataOrgRepository {
    @Autowired
    @Qualifier("organizationMongoTemplate")
    private MongoTemplate mongoTemplate;

    private Query findByQuery(CustomDataParams params, String companyUrl){
        String q = params.getQ();
        String status = StatusData.ACTIVE.getLabel();
//        String companyUrl = new ObjectId(com);
        String groupCode = params.getGroupCode();

        Query query = new Query();
        // Add mandatory filters
        query.addCriteria(Criteria.where("custom_group.code").is(groupCode));
        query.addCriteria(Criteria.where("deleted_at").is(null));
        query.addCriteria(Criteria.where("status").is(status));

        Criteria careerSiteCriteria = Criteria.where("custom_group.company.career_site_url.url").is(companyUrl);
        Criteria hrmsSiteCriteria = Criteria.where("custom_group.company.hrms_site_url.url").is(companyUrl);
        query.addCriteria(new Criteria().orOperator(careerSiteCriteria, hrmsSiteCriteria));

        // Add conditional filters based on params
        if (q != null) {
            String escapedKeyword = Pattern.quote(q);
            query.addCriteria(Criteria.where("name").regex(escapedKeyword, "i"));
        }

        return query;
    }

    @Override
    public Page<CustomData> findByParamsPaginated(CustomDataParams params, String companyUrl, Pageable pageable) {
        Query query = findByQuery(params, companyUrl);
        long count = mongoTemplate.count(query, CustomData.class);
        Sort sort = Sort.by(Sort.Direction.DESC, "date");
        query.with(pageable).with(sort);
        List<CustomData> data = mongoTemplate.find(query, CustomData.class);

        return new PageImpl<>(data, pageable, count);
    }
}