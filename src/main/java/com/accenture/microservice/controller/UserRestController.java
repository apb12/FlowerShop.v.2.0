package com.accenture.microservice.controller;


import com.accenture.microservice.entity.User;
import com.accenture.microservice.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
    @Autowired
    UserRepo userRepo;

    @RequestMapping("/rest")
    public User restUser(@RequestParam(value = "name")String name){
       return userRepo.findByUsername(name);

    }
    @RequestMapping("/cash")
    public User restAddCash(@RequestParam(value="name")String name,@RequestParam(value = "cash")Double cash){
       User u= userRepo.findByUsername(name);
       u.setCash(cash);
       userRepo.save(u);
       return u;

    }

    @RequestMapping("/check")
    public boolean checkLogin(@RequestParam(value = "username") String username){
        if(
        userRepo.findByUsername(username)==null){
            return false;}
        return true;

        }
    }

