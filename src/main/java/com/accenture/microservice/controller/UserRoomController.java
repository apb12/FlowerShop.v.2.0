package com.accenture.microservice.controller;

import com.accenture.microservice.Enums.EvidenceStatus;
import com.accenture.microservice.entity.Bucket;
import com.accenture.microservice.entity.Evidence;
import com.accenture.microservice.entity.User;
import com.accenture.microservice.service.EvidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UserRoomController {
    @Autowired
    private EvidenceService evidenceService;

    @GetMapping("/room")
    public String userRoom(Map<String, Object> model) {
        User u = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<Evidence> drafts = evidenceService.findByUserAndStatus(u, EvidenceStatus.DRAFT);
        List<Evidence> dr = new ArrayList<>();
        for (int i = 0; i < drafts.size(); i++) {
            if (drafts.get(i).getTotal() != 0) {
                dr.add(drafts.get(i));
            }
        }
        List<Evidence> drafts1 = evidenceService.findByUserAndStatus(u, EvidenceStatus.CLOSED);
        model.put("evidence", dr);
        model.put("evidence1", drafts1);
        model.put("user", u);
        return "userroom";
    }

    @PostMapping("/userroom")
    public String addToBucket() {
        User u = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<Evidence> drafts = evidenceService.findByUserAndStatus(u, EvidenceStatus.DRAFT);
        Double sum = 0.0;
        for (Bucket b : drafts.get(drafts.size() - 1).getBucket()) {
            sum += b.getSum();
        }
        drafts.get(drafts.size() - 1).setTotal(sum);
        evidenceService.save(drafts.get(drafts.size() - 1));
        return "redirect:/room";
    }

    @PostMapping("/pay")
    public String payForBucket(@RequestParam Long id) {
        Evidence e = evidenceService.findById(id);
        e.setStatus(EvidenceStatus.PAID);
        evidenceService.save(e);
        return "redirect:/";
    }

    @PostMapping("/us")
    public String showBucket(@RequestParam Long id, Map<String, Object> model) {
        Evidence ev = evidenceService.findById(id);
        List<Bucket> b = ev.getBucket();
        model.put("show", b);
        return "nextby";
    }

    @GetMapping("/nextby")
    public String nextby() {
        return "nextby";
    }
}
