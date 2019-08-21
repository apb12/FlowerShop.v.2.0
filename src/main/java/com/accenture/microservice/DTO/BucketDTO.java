package com.accenture.microservice.DTO;

import lombok.Data;

@Data
public class BucketDTO {
    private Long id;
    private Long amount;
    private Double sum;
    private FlowerDTO flower;
}
