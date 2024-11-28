package com.jobseeker.hrms.candidate.data.basic.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.jobseeker.data.PaginationParam;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CandidateSearchParamRequest extends PaginationParam {

    private ObjectId province;

    private ObjectId vacancyId;

    private ObjectId education;
}


