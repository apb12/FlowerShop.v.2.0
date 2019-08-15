package com.accenture.microservice.service;

import com.accenture.microservice.Enums.EvidenceStatus;
import com.accenture.microservice.entity.Evidence;
import com.accenture.microservice.entity.User;
import com.accenture.microservice.repos.EvidenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvidenceServiceImpl implements EvidenceService{

    @Autowired
    EvidenceRepo evidenceRepo;


    @Override
    public List<Evidence> findByUserAndStatus(User user, EvidenceStatus status) {
        return evidenceRepo.findByUserAndStatus(user,status);
    }

    @Override
    public List<Evidence> findByStatus(EvidenceStatus status) {
        return evidenceRepo.findByStatus(status);
    }

    @Override
    public void deleteById(Long id) {
        evidenceRepo.deleteById(id);

    }

    @Override
    public Evidence findById(Long id) {
        return evidenceRepo.findById(id).get();

    }

    @Override
    public void save(Evidence evidence) {
        evidenceRepo.save(evidence);
    }
}
