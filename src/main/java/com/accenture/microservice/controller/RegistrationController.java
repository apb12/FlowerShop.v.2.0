package com.accenture.microservice.controller;

import com.accenture.microservice.DTO.CaptchaResponseDTO;
import com.accenture.microservice.entity.User;
import com.accenture.microservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
@Slf4j
public class RegistrationController {

    private final static String CAPTHCA_URL="https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";
    @Autowired
    private UserService userService;
    @Autowired
    RestTemplate restTemplate;
    @Value("${recaptcha.secret}")
    private String secret;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @Valid User user, BindingResult bindingResult, Model model) {
        String url=String.format(CAPTHCA_URL,secret,captchaResponse);
       CaptchaResponseDTO responseDTO =restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDTO.class);
        if(!responseDTO.isSuccses()){
            model.addAttribute("captchaError","Bad captcha");
        }
        if(!user.getPassword().equals(user.getPassword2())){
            model.addAttribute("password2Error","Seems like this password is not the same");
            return "registration";


        }
        if(bindingResult.hasErrors() || !responseDTO.isSuccses()){
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
