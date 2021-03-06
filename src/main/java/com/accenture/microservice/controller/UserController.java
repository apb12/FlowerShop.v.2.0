package com.accenture.microservice.controller;


import com.accenture.microservice.DTO.FlowerDTO;
import com.accenture.microservice.DTO.UserDTO;
import com.accenture.microservice.Enums.Role;
import com.accenture.microservice.entity.User;
import com.accenture.microservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    ModelMapper mapper=new ModelMapper();

    @GetMapping
    public String userContr(Model model){
        List<User>ul=userService.findAll();
        Type listType = new TypeToken<List<UserDTO>>(){}.getType();
//        List<UserDTO>ul=mapper.map(userService.findAll(),listType);
        model.addAttribute("usrlist",mapper.map(ul,listType));
        return "/user";
    }
    @GetMapping("{user}")
    public String userEditRole(@PathVariable User user,Model model){
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        return"/useredit";

    }
    @PostMapping
    public String saveStatus(
            @RequestParam String username,
            @RequestParam Map<String,String>form,
            @RequestParam("userId") User user){
        user.setUsername(username);
        Set<String>roles= Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for(String key:form.keySet()){
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userService.save(user);
        log.info("Роль юезра "+user.getUsername()+" обновлена");

        return "redirect:/user";

    }
}