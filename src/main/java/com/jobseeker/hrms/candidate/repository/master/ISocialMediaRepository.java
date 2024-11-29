package com.jobseeker.hrms.candidate.repository.master;

import org.jobseeker.master.SocialMedia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISocialMediaRepository extends MongoRepository<SocialMedia, String> {
    @Query("{name: RegExp(?0, 'i')}")
    SocialMedia findByName(String socialMediaName);
}
