package com.jobseeker.hrms.candidate.repository.master;

import org.jobseeker.master.area.Province;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvinceRepository extends MongoRepository<Province, String> {
    @Query("{name: RegExp(?0, 'i')}")
    public Province findByProvinceName(String name);
}
