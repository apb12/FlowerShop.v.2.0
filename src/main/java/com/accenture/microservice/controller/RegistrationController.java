package com.accenture.microservice.controller;

import com.accenture.microservice.entity.User;
import com.accenture.microservice.repos.UserRepo;
import com.accenture.microservice.service.UserService;
import com.accenture.microservice.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RegistrationController {

   @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        if (userService.addUser(user)) {
            model.put("message", "пользователь с таким логином существует");
            return "registration";
        }
        return "redirect:/login";
    }
}
