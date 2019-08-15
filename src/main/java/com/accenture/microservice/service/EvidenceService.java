package com.accenture.microservice.service;

import com.accenture.microservice.Enums.EvidenceStatus;
import com.accenture.microservice.entity.Evidence;
import com.accenture.microservice.entity.User;

import java.util.List;

public interface EvidenceService {
    List<Evidence> findByUserAndStatus(User user, EvidenceStatus status);
    List<Evidence>findByStatus(EvidenceStatus status);
    void deleteById(Long id);
    Evidence findById(Long id);
    void save(Evidence evidence);
}
