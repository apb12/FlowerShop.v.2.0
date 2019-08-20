package com.accenture.microservice.controller;

import com.accenture.microservice.Enums.EvidenceStatus;
import com.accenture.microservice.entity.Bucket;
import com.accenture.microservice.entity.Evidence;
import com.accenture.microservice.entity.FlowerEntity;
import com.accenture.microservice.entity.User;
import com.accenture.microservice.service.BucketService;
import com.accenture.microservice.service.EvidenceService;
import com.accenture.microservice.service.FlowerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@SessionAttributes(types = Evidence.class)
public class MainController {

    @Autowired
    private FlowerService flowerService;
    @Autowired
    private EvidenceService evidenceService;
    @Autowired
    private BucketService bucketService;


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/m")
    public String view(Map<String, Object> model, @AuthenticationPrincipal User u) {
        if(u.getActivationCode()==null){
        List<FlowerEntity> flowers = flowerService.findAll();
        model.put("flowers", flowers);}
        else {
            log.info("попытка входа неактивированного аккаунта "+u.getUsername());
        model.put("message","Ваш аккаунт не активирован,пройдите аутентефикацию");}
        return "main";
    }

    @PostMapping("/main")
    public String add(Model model,@AuthenticationPrincipal User u) {
        Evidence e = new Evidence();
        e.setDate(new Date());
        e.setUser(u);
        e.setStatus(EvidenceStatus.DRAFT);
        e.setTotal(0.0);
        evidenceService.save(e);
        log.info("Заказ "+e.getId()+"сохранен в базу");
        model.addAttribute(e);
        return "redirect:/m";

    }

    @PostMapping("/ss")
    public String addBucket(@RequestParam String flowername, @RequestParam Long amount, Evidence evidence) {
        Bucket b = new Bucket();
        FlowerEntity f = flowerService.findByName(flowername);
        b.setFlower(f);
        b.setAmount(amount);
        b.setEvidence(evidence);
        b.setSum(f.getPrice() * amount);
        bucketService.save(b);
        log.info("корзина "+b.getId()+" сохранена в базу");
        return "redirect:/m";
    }
}

