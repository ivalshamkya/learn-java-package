package com.jobseeker.hrms.candidate.repository.master;

import org.jobseeker.master.area.District;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IDistrictRepository extends MongoRepository<District, String> {
    @Query("{'city._id': ObjectId(?0), name:RegExp(?1, 'i')}")
    District findByCityIdAndDistrictName(String cityId, String districtName);
}
