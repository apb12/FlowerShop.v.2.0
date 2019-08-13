package com.accenture.microservice.repos;

import com.accenture.microservice.entity.FlowerEntity;
import org.springframework.data.repository.CrudRepository;

public interface FlowerRepo extends CrudRepository<FlowerEntity,Long> {
    FlowerEntity findByName(String flowername);

}
