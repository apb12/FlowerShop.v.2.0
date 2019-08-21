package com.accenture.microservice.DTO;

import com.accenture.microservice.Enums.EvidenceStatus;
import lombok.Data;

import java.util.Date;

@Data
public class EvidenceDTO {
    private Long id;
    private Date date;
    private Double total;
    private EvidenceStatus status;

}
