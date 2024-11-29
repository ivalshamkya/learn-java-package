package com.jobseeker.hrms.candidate.data.basic.request.explore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.jobseeker.data.PaginationParam;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExploreDataRequest extends PaginationParam {

    private String lat = "0";
    private String lng = "0";

    private Boolean isVideo = false;
    private Boolean isRecommendation = false;
    private String keyword;

    private String industryTypeId;
    private String provinceId;
    private String cityId;
    private String genderId;
    private String educationId;
    private List<String> jobFunctionIds;
    private List<String> jobFuncIdsBasedOnCategory;

}
