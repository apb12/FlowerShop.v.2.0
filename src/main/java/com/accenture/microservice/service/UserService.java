package com.accenture.microservice.service;

import com.accenture.microservice.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
     boolean addUser(User user);
     UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
     User findByUsername(String username);
     void save(User user);
     User findById(Long id);
     List<User> findAll();
     boolean activated(String code);
}
