package com.jobseeker.hrms.candidate.repository.master;

import org.jobseeker.master.area.SubDistrict;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ISubDistrictRepository extends MongoRepository<SubDistrict, String> {
    @Query("{'district._id': ObjectId(?0), name:RegExp(?1, 'i')}")
    SubDistrict findByDistrictIdAndSubDistrictName(String cityId, String districtName);
}
