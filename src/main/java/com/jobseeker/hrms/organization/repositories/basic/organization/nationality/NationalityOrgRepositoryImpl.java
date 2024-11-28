package com.jobseeker.hrms.organization.repositories.basic.organization.nationality;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobseeker.data.PaginationParam;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.Nationality;
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
public class NationalityOrgRepositoryImpl implements NationalityOrgRepositoryExtend {
    @Autowired
    @Qualifier("organizationMongoTemplate")
    private MongoTemplate mongoTemplate;
    
    @Override
    public Page<Nationality> findByDataTables(PaginationParam param) {
        String companyId = ServletRequestAWS.getCompanyId();
        Query query = new Query();
        query.addCriteria(Criteria.where("company._id").is(companyId));
        query.addCriteria(Criteria.where("status").is(StatusData.ACTIVE));
        
        String keyword = param.getQ();
        if(keyword != null) {
            String escapedKeyword = Pattern.quote(keyword);
            query.addCriteria(new Criteria().orOperator(
                Criteria.where("name").regex(escapedKeyword, "i"),
                Criteria.where("code").regex(escapedKeyword, "i")
            ));
        }
        
        int page = param.getPage() - 1;
        Pageable pageable = PageRequest.of(
            page, param.getLimit(),
            param.getSortDirection(), param.getSortedField().getDatabaseFieldName()
        );
        query.with(pageable);
        
        List<Nationality> resultList = mongoTemplate.find(query, Nationality.class);
        long total = mongoTemplate.count(query, Nationality.class);
        return new PageImpl<>(resultList, pageable, total);
    }
}
