package com.accenture.microservice.DTO;

import lombok.Data;

@Data
public class FlowerDTO {
    private Long id;
    private String name;
    private Double price;
    private Long amount;
}
