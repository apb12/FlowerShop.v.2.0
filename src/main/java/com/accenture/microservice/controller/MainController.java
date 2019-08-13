package com.accenture.microservice.controller;

import com.accenture.microservice.Enums.EvidenceStatus;
import com.accenture.microservice.entity.Bucket;
import com.accenture.microservice.entity.Evidence;
import com.accenture.microservice.entity.FlowerEntity;
import com.accenture.microservice.entity.User;
import com.accenture.microservice.repos.BucketRepo;
import com.accenture.microservice.repos.EvidenceRepo;
import com.accenture.microservice.repos.FlowerRepo;
import com.accenture.microservice.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Date;
import java.util.Map;

@Controller
@SessionAttributes(types = Evidence.class)
public class MainController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private FlowerRepo flowerRepo;
    @Autowired
    private EvidenceRepo evidenceRepo;
    @Autowired
    private BucketRepo bucketRepo;


    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/m")
    public String view(Map<String, Object> model) {
        Iterable<FlowerEntity> flowers = flowerRepo.findAll();
        model.put("flowers", flowers);
        // Object pr= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return "main";
    }

    @PostMapping("/main")
    public String add(Model model) {
        Evidence e = new Evidence();
        e.setDate(new Date());
        e.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        e.setStatus(EvidenceStatus.DRAFT);
        e.setTotal(0.0);
        evidenceRepo.save(e);
        model.addAttribute(e);
        return "redirect:/m";

    }

    @PostMapping("/ss")
    public String addBucket(@RequestParam String flowername, @RequestParam Long amount, Evidence evidence) {
        Bucket b = new Bucket();
        FlowerEntity f = flowerRepo.findByName(flowername);
        b.setFlower(f);
        b.setAmount(amount);
        b.setEvidence(evidence);
        b.setSum(f.getPrice() * amount);
        bucketRepo.save(b);
        return "redirect:/m";
    }
}

