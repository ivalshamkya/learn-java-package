package com.jobseeker.hrms.candidate.data.basic.response;

import com.jobseeker.hrms.candidate.data.basic.response.component.CandidateSimpleDataResponse;
import com.jobseeker.hrms.candidate.data.basic.response.component.HistoryDataResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CandidateHistoryResponse {
    private CandidateSimpleDataResponse candidate;
    private List<HistoryDataResponse> history;
}
