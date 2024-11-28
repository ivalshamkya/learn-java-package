package com.jobseeker.hrms.organization.repositories.basic.organization.customGroup;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationParams;
import org.bson.types.ObjectId;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.CustomGroupData;
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
public class CustomGroupDataOrgRepositoryImpl implements CustomGroupDataOrgRepository {
    @Autowired
    @Qualifier("organizationMongoTemplate")
    private MongoTemplate mongoTemplate;

    private Query findByQuery(PaginationParams params){
        String q = params.getQ();
        String status = StatusData.ACTIVE.getLabel();
        ObjectId companyId = new ObjectId(ServletRequestAWS.getCompanyId());

        Query query = new Query();
        // Add mandatory filters
        query.addCriteria(Criteria.where("company._id").is(companyId));
        query.addCriteria(Criteria.where("deleted_at").is(null));
        query.addCriteria(Criteria.where("status").is(status));

        // Add conditional filters based on params
        if (q != null) {
            String escapedKeyword = Pattern.quote(q);
            query.addCriteria(Criteria.where("name").regex(escapedKeyword, "i"));
        }
        return query;
    }

    @Override
    public Page<CustomGroupData> findByParamsPaginated(PaginationParams params, Pageable pageable) {
        Query query = findByQuery(params);
        long count = mongoTemplate.count(query, CustomGroupData.class);
        Sort sort = Sort.by(Sort.Direction.DESC, "date");
        query.with(pageable).with(sort);
        List<CustomGroupData> data = mongoTemplate.find(query, CustomGroupData.class);

        return new PageImpl<>(data, pageable, count);
    }
}