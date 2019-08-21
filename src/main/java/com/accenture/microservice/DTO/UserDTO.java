package com.accenture.microservice.DTO;

import com.accenture.microservice.Enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private Double cash;
    private Double discount;
    private String email;
    private Set<Role> roles;
}
