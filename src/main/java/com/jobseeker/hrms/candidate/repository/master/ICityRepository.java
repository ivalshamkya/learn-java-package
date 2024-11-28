package com.jobseeker.hrms.candidate.repository.master;

import org.jobseeker.master.area.City;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICityRepository extends MongoRepository<City, String> {
    @Query("{'province._id': ObjectId(?0), name:RegExp(?1, 'i')}")
    City findByProvinceIdAndCityName(String provinceId, String cityName);
}
