package com.jobseeker.hrms.organization.repositories.basic.organization.houseStatus;

import com.jobseeker.hrms.organization.config.ServletRequestAWS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobseeker.data.PaginationParam;
import org.jobseeker.enums.general.StatusData;
import org.jobseeker.organization.HardSkillOrganization;
import org.jobseeker.organization.HouseStatus;
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
import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
@Component
@RequiredArgsConstructor
public class HouseStatusOrgOrgRepositoryImpl implements HouseStatusOrgOrgRepositoryExtend {
    @Autowired
    @Qualifier("organizationMongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public Page<HouseStatus> findByDataTables(PaginationParam param) {
        String companyId = ServletRequestAWS.getCompanyId();
        Criteria companyCriteria = Criteria.where("company._id").is(companyId);
        Criteria statusCriteria = Criteria.where("status").is(StatusData.ACTIVE);
        Criteria keywordCriteria = new Criteria();

        String keyword = param.getQ();
        if (keyword != null) {
            String escapedKeyword = Pattern.quote(keyword);
            keywordCriteria = Criteria.where("name.id").regex(escapedKeyword, "i")
                    .orOperator(Criteria.where("name.en").regex(escapedKeyword, "i"));
        }

        int page = param.getPage() - 1;
        Pageable pageable = PageRequest.of(
                page, param.getLimit(),
                param.getSortDirection(), param.getSortedField().getDatabaseFieldName()
        );

        Criteria searchCriteria = new Criteria().andOperator(
                companyCriteria, statusCriteria, keywordCriteria
        );
        Query queryBuilder = Query.query(searchCriteria).with(pageable);


        List<HouseStatus> resultList = mongoTemplate.find(queryBuilder, HouseStatus.class);
        long total = mongoTemplate.count(Query.query(searchCriteria), HouseStatus.class);

        return new PageImpl<>(resultList, pageable, total);
    }

    @Override
    public Optional<HouseStatus> findFirstByActive(String houseStatusId) {
        String companyId = ServletRequestAWS.getCompanyId();
        Query query = new Query(Criteria.where("_id").is(houseStatusId)
                .and("status").is(StatusData.ACTIVE)
                .and("company._id").is(companyId)
        );

        return Optional.ofNullable(mongoTemplate.findOne(query, HouseStatus.class));
    }
}
