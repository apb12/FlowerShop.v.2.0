package com.accenture.microservice.service;

import com.accenture.microservice.Enums.Role;
import com.accenture.microservice.entity.User;
import com.accenture.microservice.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUsername(s);
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id).get();
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public boolean addUser(User user) {
        User userDB = userRepo.findByUsername(user.getUsername());
        if (userDB != null) {
            return true;
        }
        user.setCash(2000.0);
        user.setDiscount(10.0);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return false;
    }
}
