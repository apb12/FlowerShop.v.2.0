package com.accenture.microservice.service;

import com.accenture.microservice.entity.Bucket;
import com.accenture.microservice.repos.BucketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BucketServiceImpl implements BucketService {

    @Autowired
    private BucketRepo bucketRepo;
    @Override
    public void save(Bucket bucket) {
        bucketRepo.save(bucket);

    }
}
