package com.accenture.microservice.controller;

import com.accenture.microservice.entity.FlowerEntity;
import com.accenture.microservice.repos.EvidenceRepo;
import com.accenture.microservice.repos.FlowerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AdminController {

    @Autowired
    FlowerRepo flowerRepo;
    @Autowired
    EvidenceRepo evidenceRepo;

    @GetMapping("admin")
    public String view(Map<String, Object> model) {
        Iterable<FlowerEntity> flowers = flowerRepo.findAll();
        model.put("flowers", flowers);
        return "admin";
    }

    @PostMapping("admin")
    public String add(@RequestParam String name, @RequestParam Double price, @RequestParam Long amount, Map<String, Object> model) {
        FlowerEntity f = new FlowerEntity();
        f.setName(name);
        f.setPrice(price);
        f.setAmount(amount);
        flowerRepo.save(f);
        return "redirect:/admin";

    }

    @PostMapping("addamount")
    public String addAmount(@RequestParam Long id, @RequestParam Long amount) {
        FlowerEntity f = flowerRepo.findById(id).get();
        f.setAmount(f.getAmount() + amount);
        flowerRepo.save(f);
        return "redirect:/admin";

    }

    @PostMapping("newprice")
    public String newPrice(@RequestParam Long id, @RequestParam Long price) {
        FlowerEntity f = flowerRepo.findById(id).get();
        f.setAmount(price);
        flowerRepo.save(f);
        return "redirect:/admin";

    }

    @PostMapping("delete")
    public String deleteFlower(@RequestParam Long id) {
        flowerRepo.deleteById(id);
        return "redirect:/admin";

    }
}
