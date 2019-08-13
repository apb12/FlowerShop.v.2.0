package com.accenture.microservice.controller;

import com.accenture.microservice.Enums.Role;
import com.accenture.microservice.entity.User;
import com.accenture.microservice.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userDB = userRepo.findByUsername(user.getUsername());
        if (userDB != null) {
            model.put("messages", "пользователь с таким логином существует");
            return "registration";
        }
        user.setCash(2000.0);
        user.setDiscount(10.0);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }
}
