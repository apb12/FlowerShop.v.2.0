package com.accenture.microservice.service;

import com.accenture.microservice.entity.FlowerEntity;
import com.accenture.microservice.repos.FlowerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FlowerServiceImpl implements FlowerService {

    @Autowired
    FlowerRepo flowerRepo;
    @Override
    public FlowerEntity findByName(String flowername) {
        return flowerRepo.findByName(flowername);
    }

    @Override
    public void save(FlowerEntity flowerEntity) {
        flowerRepo.save(flowerEntity);

    }

    @Override
    public List<FlowerEntity> findAll() {
        return (List<FlowerEntity>) flowerRepo.findAll();
    }

    @Override
    public FlowerEntity findById(Long id) {
        return flowerRepo.findById(id).get();
    }

    @Override
    public void deleteById(Long id) {
        flowerRepo.deleteById(id);

    }
}
