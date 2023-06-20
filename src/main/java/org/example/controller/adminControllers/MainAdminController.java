package org.example.controller.adminControllers;

import lombok.extern.log4j.Log4j2;
import org.example.model.UserEntity;
import org.example.repository.SessionRepository;
import org.example.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Controller
@RequestMapping("/admin")
@Component("adminMainController")
public class MainAdminController {
    final
    UserDetailsServiceImpl userDetailsService;

    final
    SessionRepository sessionRepository;
    @Autowired
    public MainAdminController(UserDetailsServiceImpl userDetailsService, SessionRepository sessionRepository) {
        this.userDetailsService = userDetailsService;
        this.sessionRepository = sessionRepository;
    }
    Map<String,Integer> dateCountMap =new HashMap<>();

    @GetMapping("/")
    public String main(Model model) {
        List<UserEntity> userEntityList= userDetailsService.findAll();
        model.addAttribute("userSize",userEntityList.size());
        double param=(double) userEntityList.stream().filter(user -> user.getGender()==true).count()/userEntityList.size()*100;
        model.addAttribute("male",(int) param);
        log.info((double)userEntityList.stream().filter(user -> user.getGender()==true).count()/userEntityList.size()*100);

        sessionRepository.findAll().stream().forEach(sessionEntity -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Integer count=dateCountMap.getOrDefault(dateFormat.format(sessionEntity.getTime()),0);
            dateCountMap .put(dateFormat.format(sessionEntity.getTime()),count+1);
        });
        return "/admin/main";
    }

    @GetMapping("/getSessions")
    @ResponseBody
    public Map getPercents() {
        Map<String,Integer> elements=dateCountMap ;
        log.info(dateCountMap);
        return  elements;
    }
}
