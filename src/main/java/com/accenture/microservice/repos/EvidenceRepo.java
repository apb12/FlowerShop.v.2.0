package com.accenture.microservice.repos;

import com.accenture.microservice.Enums.EvidenceStatus;
import com.accenture.microservice.entity.Evidence;
import com.accenture.microservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvidenceRepo  extends JpaRepository<Evidence,Long> {
    List<Evidence>findByUserAndStatus(User user, EvidenceStatus status);
    List<Evidence>findByStatus(EvidenceStatus status);

}
