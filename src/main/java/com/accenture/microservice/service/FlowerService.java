package com.accenture.microservice.service;

import com.accenture.microservice.entity.FlowerEntity;

import java.util.List;

public interface FlowerService {
    FlowerEntity findByName(String flowername);
    void save(FlowerEntity flowerEntity);
    List<FlowerEntity>findAll();
    FlowerEntity findById(Long id);
    void deleteById(Long id);
}
