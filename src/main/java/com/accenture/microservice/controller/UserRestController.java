package com.accenture.microservice.controller;


import com.accenture.microservice.DTO.EvidenceDTO;
import com.accenture.microservice.DTO.UserDTO;
import com.accenture.microservice.entity.User;
import com.accenture.microservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@Slf4j
@RestController
public class UserRestController {
    @Autowired
    UserService userService;
    ModelMapper mapper=new ModelMapper();

    @RequestMapping("/rest")
    public UserDTO restUser(@RequestParam(value = "name")String name){
       return mapper.map(userService.findByUsername(name),UserDTO.class);

    }
    @RequestMapping("/cash")
    public UserDTO restAddCash(@RequestParam(value="name")String name,@RequestParam(value = "cash")Double cash){
       User u= userService.findByUsername(name);
       u.setCash(cash);
       log.info("Баланс пользотвателя "+u.getUsername()+ "увеличен на "+cash);
       userService.save(u);
       return mapper.map(u,UserDTO.class);

    }

    @RequestMapping("/check")
    public boolean checkLogin(@RequestParam(value = "username") String username){
        if(
        userService.findByUsername(username)==null){
            return false;}
        return true;

        }
        @RequestMapping("/findall")

        public List <User> getAllUsers(){
          //  Type listType = new TypeToken<List<UserDTO>>(){}.getType();
        //return mapper.map(userService.findAll(),listType);
            return userService.findAll();
        }
    }

