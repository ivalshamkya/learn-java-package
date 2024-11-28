package com.jobseeker.hrms.organization.repositories.basic.organization.reasonOrganization;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.reasonOrganization.ReasonOrganizationDataRequest;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.ReasonOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.regex.Pattern;

public class ReasonOrganizationOrgOrgRepositoryImpl implements ReasonOrganizationOrgOrgRepositoryExtend {
    @Autowired
    @Qualifier("organizationMongoTemplate")
    private MongoTemplate mongoTemplate;
    
    @Override
    public Page<ReasonOrganization> findDataTables(ReasonOrganizationDataRequest params) {
        String companyId = ServletRequestAWS.getCompanyId();
        Query query = new Query();
        query.addCriteria(Criteria.where("company._id").is(companyId));
        query.addCriteria(Criteria.where("status").is(StatusData.ACTIVE));
        
        String type = params.getType();
        if(type != null) {
            query.addCriteria(Criteria.where("type").is(type));
        }
        
        String keyword = params.getQ();
        
        if(keyword != null){
            String escapedKeyword = Pattern.quote(keyword);
            query.addCriteria(new Criteria().orOperator(
                Criteria.where("name.en").regex(escapedKeyword, "i"),
                Criteria.where("name.id").regex(escapedKeyword, "i")
            ));
        }
        int page = params.getPage() - 1;
        Pageable pageable = PageRequest.of(
            page, params.getLimit(),
            params.getSortDirection(), params.getSortedField().getDatabaseFieldName()
        );
        query.with(pageable);
        
        List<ReasonOrganization> resultList = mongoTemplate.find(query, ReasonOrganization.class);
        long total = mongoTemplate.count(query, ReasonOrganization.class);
        return new PageImpl<>(resultList, pageable, total);
    }
}
