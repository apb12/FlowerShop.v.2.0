package com.accenture.microservice.controller;

import com.accenture.microservice.entity.User;
import com.accenture.microservice.service.FlowerService;
import com.accenture.microservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowerRestController {

    @Autowired
    UserService userService;
    @Autowired
    FlowerService flowerService;

    @RequestMapping("/validate")
    public boolean validateOrder(@RequestParam(value = "flowername")String name, @RequestParam(name = "amount")Long amount){
        User user= (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.getCash()<(flowerService.findByName(name).getPrice()*amount) || flowerService.findByName(name).getAmount()<amount){
            return true;}
        return false;
        }
    }

