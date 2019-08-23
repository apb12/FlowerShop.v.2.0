package com.accenture.microservice.controller;

import com.accenture.microservice.entity.User;
import com.accenture.microservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@Slf4j
public class RegistrationController {



   @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {

        if(!user.getPassword().equals(user.getPassword2())){
            model.addAttribute("password2Error","Seems like this password is not the same");
            return "registration";


        }
        if(bindingResult.hasErrors()){
            Map<String,String>errors=ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }
        if (userService.addUser(user)) {
            model.addAttribute("usernameError", "пользователь с таким логином существует");
            log.error(user.getUsername()+" такой пользователь существует");
            return "registration";
        }
        log.info("юзер "+user.getUsername()+" добавлен в базу данных");
        return "redirect:/login";
    }
    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated=userService.activated(code);
        if(isActivated){
            model.addAttribute("message","Активация прошла успешно");
            log.info("активация прошла успешно");
        }
        else model.addAttribute("message","Активация не удалась");
        log.info("ошибка активации");
        return "login";

    }
}
