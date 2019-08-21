package com.accenture.microservice.controller;

import com.accenture.microservice.DTO.BucketDTO;
import com.accenture.microservice.DTO.EvidenceDTO;
import com.accenture.microservice.DTO.UserDTO;
import com.accenture.microservice.Enums.EvidenceStatus;
import com.accenture.microservice.entity.Bucket;
import com.accenture.microservice.entity.Evidence;
import com.accenture.microservice.entity.User;
import com.accenture.microservice.service.EvidenceService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Slf4j
@Controller
public class UserRoomController {
    @Autowired
    private EvidenceService evidenceService;

    ModelMapper mapper=new ModelMapper();

    @GetMapping("/room")
    public String userRoom(Map<String, Object> model, @AuthenticationPrincipal User u) {
        Type listType = new TypeToken<List<EvidenceDTO>>(){}.getType();
        List<Evidence> drafts = evidenceService.findByUserAndStatus(u, EvidenceStatus.DRAFT);
        List<Evidence> dr = new ArrayList<>();
        for (int i = 0; i < drafts.size(); i++) {
            if (drafts.get(i).getTotal() != 0) {
                dr.add(drafts.get(i));
            }
        }
        List<Evidence> drafts1 = evidenceService.findByUserAndStatus(u, EvidenceStatus.CLOSED);
        model.put("evidence", mapper.map(dr,listType));
        model.put("evidence1", mapper.map(drafts1,listType));
        model.put("user", mapper.map(u, UserDTO.class));
        return "userroom";
    }

    @PostMapping("/userroom")
    public String addToBucket(@AuthenticationPrincipal User u) {
        List<Evidence> drafts = evidenceService.findByUserAndStatus(u, EvidenceStatus.DRAFT);
        Double sum = 0.0;
        for (Bucket b : drafts.get(drafts.size() - 1).getBucket()) {
            sum += b.getSum();
        }
        drafts.get(drafts.size() - 1).setTotal(sum);
        evidenceService.save(drafts.get(drafts.size() - 1));
        log.info("в заказ "+drafts.get(drafts.size()-1).getId()+"добавлены новые позиции");
        return "redirect:/room";
    }

    @PostMapping("/pay")
    public String payForBucket(@RequestParam Long id) {
        Evidence e = evidenceService.findById(id);
        e.setStatus(EvidenceStatus.PAID);
        log.info("заказ "+id+" обновлен до статуса "+EvidenceStatus.PAID);
        evidenceService.save(e);
        return "redirect:/";
    }

    @PostMapping("/us")
    public String showBucket(@RequestParam Long id, Map<String, Object> model) {
        Type listType = new TypeToken<List<BucketDTO>>(){}.getType();
        Evidence ev = evidenceService.findById(id);
        List<Bucket> b = ev.getBucket();
        model.put("show", mapper.map(b,listType));
        return "nextby";
    }

    @GetMapping("/nextby")
    public String nextby() {
        return "nextby";
    }
}
