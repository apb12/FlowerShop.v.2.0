package com.accenture.microservice.controller;

import com.accenture.microservice.Enums.EvidenceStatus;
import com.accenture.microservice.entity.Bucket;
import com.accenture.microservice.entity.Evidence;
import com.accenture.microservice.entity.User;
import com.accenture.microservice.repos.EvidenceRepo;
import com.accenture.microservice.repos.FlowerRepo;
import com.accenture.microservice.repos.UserRepo;
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
public class AdminEvidenceController {

    @Autowired
    EvidenceRepo evidenceRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    FlowerRepo flowerRepo;

    @GetMapping("/admin2")
    public String view(Map<String, Object> model) {
        List<Evidence> evidenceRepoList1 = evidenceRepo.findByStatus(EvidenceStatus.PAID);
        model.put("evidence1", evidenceRepoList1);
        List<Evidence> evidenceRepoList2 = evidenceRepo.findByStatus(EvidenceStatus.DRAFT);
        model.put("evidence2", evidenceRepoList2);
        List<Evidence> evidenceRepoList3 = evidenceRepo.findByStatus(EvidenceStatus.CLOSED);
        model.put("evidence3", evidenceRepoList3);
        return "admin2";
    }

    @PostMapping("admin2")
    public String deleteDrafts(@RequestParam Long id) {
        evidenceRepo.deleteById(id);
        return "redirect:/admin2";

    }

    @PostMapping("admin3")
    public String doEvidence(@RequestParam Long id) {
        Evidence e = evidenceRepo.findById(id).get();
        User u = userRepo.findById(e.getUser().getId()).get();
        if(u.getCash()>(e.getTotal() * ((1 - (u.getDiscount() / 100))))){
        u.setCash(u.getCash() - (e.getTotal() * ((1 - (u.getDiscount() / 100)))));
        userRepo.save(u);
            e.setStatus(EvidenceStatus.CLOSED);
            evidenceRepo.save(e);
        for (Bucket b : e.getBucket()) {
            b.getFlower().setAmount(b.getFlower().getAmount() - b.getAmount());
            flowerRepo.save(b.getFlower());
        }
        return "redirect:/admin2";}
        else e.setStatus(EvidenceStatus.DRAFT);
        evidenceRepo.save(e);
        return "redirect:/admin2";

    }
}
