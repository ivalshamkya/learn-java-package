package com.jobseeker.hrms.organization.repositories.lws.organization.candidateLanguage;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.lws.paginationParam.PaginationLWSParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobseeker.organization.CandidateLanguage;
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
public class CandidateLanguageLWSRepositoryImpl implements CandidateLanguageLWSRepositoryExtend {
    @Autowired
    @Qualifier("organizationMongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public Page<CandidateLanguage> findDataTables(PaginationLWSParams param) {
        String companyId = ServletRequestAWS.getCompanyId();

        Criteria companyCriteria = Criteria.where("company._id").is(companyId);
        Criteria keywordCriteria = new Criteria();

        String keyword = param.getQ();
        if (keyword != null) {
            String escapedKeyword = Pattern.quote(keyword);
            keywordCriteria = Criteria.where("name").regex(escapedKeyword, "i");
        }

        int page = param.getPage() - 1;
        Pageable pageable = PageRequest.of(
                page, param.getLimit(),
                param.getSortDirection(), param.getSortedField().getDatabaseFieldName()
        );

        Criteria searchCriteria = new Criteria().andOperator(
                companyCriteria, keywordCriteria
        );

        Query queryBuilder = Query.query(searchCriteria).with(pageable);


        List<CandidateLanguage> resultList = mongoTemplate.find(queryBuilder, CandidateLanguage.class);
        long total = mongoTemplate.count(Query.query(searchCriteria), CandidateLanguage.class);

        return new PageImpl<>(resultList, pageable, total);
    }
}
