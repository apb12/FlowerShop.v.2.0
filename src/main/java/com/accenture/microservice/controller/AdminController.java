package com.accenture.microservice.controller;

import com.accenture.microservice.entity.FlowerEntity;
import com.accenture.microservice.service.FlowerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@Slf4j
public class AdminController {

    @Autowired
    FlowerService flowerService;

    @GetMapping("admin")
    public String view(Map<String, Object> model) {
        List<FlowerEntity> flowers = flowerService.findAll();
        model.put("flowers", flowers);
        return "admin";
    }

    @PostMapping("admin")
    public String add(@RequestParam String name, @RequestParam Double price, @RequestParam Long amount, Map<String, Object> model) {
        FlowerEntity f = new FlowerEntity();
        f.setName(name);
        f.setPrice(price);
        f.setAmount(amount);
        flowerService.save(f);
        log.info(f.getName()+" сохранен в базу");
        return "redirect:/admin";

    }

    @PostMapping("addamount")
    public String addAmount(@RequestParam Long id, @RequestParam Long amount) {
        FlowerEntity f = flowerService.findById(id);
        f.setAmount(f.getAmount() + amount);
        flowerService.save(f);
        log.info(f.getName() +"количество увеличено на "+amount);
        return "redirect:/admin";

    }

    @PostMapping("newprice")
    public String newPrice(@RequestParam Long id, @RequestParam Double price) {
        FlowerEntity f = flowerService.findById(id);
        f.setPrice(price);
        flowerService.save(f);
        log.info(f.getName()+" цена изменена на "+price);
        return "redirect:/admin";

    }

    @PostMapping("delete")
    public String deleteFlower(@RequestParam Long id) {
        flowerService.deleteById(id);
        log.info("цветок с "+id+ "удален со склада");
        return "redirect:/admin";

    }
}
