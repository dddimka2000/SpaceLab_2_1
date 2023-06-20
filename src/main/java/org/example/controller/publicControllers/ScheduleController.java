package org.example.controller.publicControllers;

import org.example.model.FilmEntity;
import org.example.model.HallEntity;
import org.example.model.RelatedFilmsHallsEntity;
import org.example.service.FilmService;
import org.example.service.HallService;
import org.example.service.RelatedFilmsHallsEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ScheduleController {

    final
    RelatedFilmsHallsEntityService relatedFilmsHallsEntityService;


    @Autowired
    public ScheduleController(RelatedFilmsHallsEntityService relatedFilmsHallsEntityService) {
        this.relatedFilmsHallsEntityService = relatedFilmsHallsEntityService;
    }

    @GetMapping("/schedule")
    public String showSchedule(Model model) {
        List<RelatedFilmsHallsEntity> relatedFilmsHallsEntityList = relatedFilmsHallsEntityService.findAll();
        model.addAttribute("relatedFilmsHallsEntityList",relatedFilmsHallsEntityList);
        return "public/schedule";
    }
    @GetMapping("/schedule/{id}")
    public String showScheduleFilm(Model model, @PathVariable Integer id) {
        FilmEntity film=relatedFilmsHallsEntityService.findById(id).get().getFilm();
        HallEntity hall=relatedFilmsHallsEntityService.findById(id).get().getHall();
        model.addAttribute("film", film);
        model.addAttribute("hall", hall);
        model.addAttribute("relatedFilmsHallsEntityService",relatedFilmsHallsEntityService.findById(id).get());
        return "public/bookTicket";
    }


    }
