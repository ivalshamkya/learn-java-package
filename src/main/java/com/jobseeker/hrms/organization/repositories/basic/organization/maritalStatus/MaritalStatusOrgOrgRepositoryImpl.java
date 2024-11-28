package com.jobseeker.hrms.organization.repositories.basic.organization.maritalStatus;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobseeker.data.PaginationParam;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.MaritalStatusOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@Component
@RequiredArgsConstructor
public class MaritalStatusOrgOrgRepositoryImpl implements MaritalStatusOrgOrgRepositoryExtend {
    @Autowired
    @Qualifier("organizationMongoTemplate")
    private MongoTemplate mongoTemplate;
    
    @Override
    public Page<MaritalStatusOrganization> findByDataTables(PaginationParam param) {
        String companyId = ServletRequestAWS.getCompanyId();
        Query query = new Query();
        query.addCriteria(Criteria.where("company._id").is(companyId));
        query.addCriteria(Criteria.where("status").is(StatusData.ACTIVE));
        
        String keyword = param.getQ();
        if(keyword != null) {
            String escapedKeyword = Pattern.quote(keyword);
            query.addCriteria(new Criteria().orOperator(
                Criteria.where("name.en").regex(escapedKeyword, "i"),
                Criteria.where("name.id").regex(escapedKeyword, "i"),
                Criteria.where("code").regex(escapedKeyword, "i")
            ));
        }
        
        int page = param.getPage() - 1;
        Pageable pageable = PageRequest.of(
            page, param.getLimit(),
            param.getSortDirection(), param.getSortedField().getDatabaseFieldName()
        );
        query.with(pageable);
        List<MaritalStatusOrganization> resultList = mongoTemplate.find(query, MaritalStatusOrganization.class);
        long total = mongoTemplate.count(query, MaritalStatusOrganization.class);
        return new PageImpl<>(resultList, pageable, total);
    }
}
