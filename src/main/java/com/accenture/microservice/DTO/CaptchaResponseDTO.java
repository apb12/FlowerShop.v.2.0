package com.accenture.microservice.DTO;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptchaResponseDTO {
    private boolean succses;
    @JsonAlias("error-codes")
    private Set<String>errorcodes;
}
