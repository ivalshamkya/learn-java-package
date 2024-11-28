package com.jobseeker.hrms.organization.repositories.sms.organization.importLog;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import com.jobseeker.hrms.organization.data.basic.paginationParam.PaginationParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobseeker.logs.ImportLog;
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
public class ImportLogSMSRepositoryImpl implements ImportLogSMSRepositoryExtend  {
    @Autowired
    @Qualifier("logsMongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public Page<ImportLog> findDataTables(PaginationParams params) {
        String companyId = ServletRequestAWS.getCompanyId();
        Query query = new Query();

        query.addCriteria(Criteria.where("company._id").is(companyId));

        String keyWord = params.getQ();
        Criteria keywordCriteria = new Criteria();
        if(keyWord != null) {
            String escapedKeyword = Pattern.quote(keyWord);
            Criteria regexName = Criteria.where("file").regex(escapedKeyword, "i");
            // Use Criteria.orOperator to combine the regex conditions
            Criteria combinedCriteria = new Criteria().orOperator(regexName);
            // Add the combined criteria to the query
            query.addCriteria(combinedCriteria);
        }
        String[] sortByFields = {"created_at"};

        int page = params.getPage() - 1;
        Pageable pageable = PageRequest.of(
                page, params.getLimit(),
                params.getSortDirection(), sortByFields
        );
        long total = mongoTemplate.count(query, ImportLog.class);
        query.with(pageable);

        List<ImportLog> resultList = mongoTemplate.find(query, ImportLog.class);

        return new PageImpl<>(resultList, pageable, total);
    }
}
