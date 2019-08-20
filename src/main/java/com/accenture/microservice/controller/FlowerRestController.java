package com.accenture.microservice.controller;

import com.accenture.microservice.entity.User;
import com.accenture.microservice.service.FlowerService;
import com.accenture.microservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FlowerRestController {

    @Autowired
    UserService userService;
    @Autowired
    FlowerService flowerService;

    @RequestMapping("/validate")
    public boolean validateOrder(@AuthenticationPrincipal User user,@RequestParam(value = "flowername")String name, @RequestParam(name = "amount")Long amount){
//        User user= (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.getCash()<(flowerService.findByName(name).getPrice()*amount) || flowerService.findByName(name).getAmount()<amount){
            log.info("валидация успешна "+user.getUsername());
            return true;}
        log.error("валидация не успешна "+user.getUsername());
        return false;
        }
    }

