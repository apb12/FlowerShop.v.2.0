package com.accenture.microservice.service;

import com.accenture.microservice.Enums.Role;
import com.accenture.microservice.entity.User;
import com.accenture.microservice.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    MailSender mailSender;
    @Autowired
    PasswordEncoder passwordEncoder;
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
    public boolean activated(String code) {
        User user=userRepo.findByActivationCode(code);
        if(user==null){
        return false ;}
        user.setActivationCode(null);
        userRepo.save(user);
        return true;
    }

    @Override
    public boolean addUser(User user) {
        User userDB = userRepo.findByUsername(user.getUsername());
        if (userDB != null) {
            return true;
        }
        user.setActivationCode(UUID.randomUUID().toString());
        user.setCash(2000.0);
        user.setDiscount(10.0);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        if(!StringUtils.isEmpty(user.getEmail())){
            String message=String.format(
                    "Привет,%s\n," +
                            "перейди по этой ссылке для активации учетной записи " +
                            "http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(),"Activation code",message);
        }
        return false;
    }
}
