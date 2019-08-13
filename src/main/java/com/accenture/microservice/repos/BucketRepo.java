package com.accenture.microservice.repos;

import com.accenture.microservice.entity.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepo extends JpaRepository<Bucket,Long> {

}
