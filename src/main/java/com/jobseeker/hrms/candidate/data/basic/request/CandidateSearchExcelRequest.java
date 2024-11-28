package com.jobseeker.hrms.candidate.data.basic.request;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateSearchExcelRequest {

    @Nullable
    private String q;

    @Nullable
    private ObjectId province;

    @Nullable
    private String vacancyId;

    @Nullable
    private ObjectId education;

}
