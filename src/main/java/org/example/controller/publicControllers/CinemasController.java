package org.example.controller.publicControllers;

import lombok.extern.log4j.Log4j2;
import org.example.model.CinemaEntity;
import org.example.model.FilmEntity;
import org.example.model.HallEntity;
import org.example.service.CinemaService;
import org.example.service.HallService;
import org.example.service.RelatedFilmsHallsEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Controller
public class CinemasController {
    final
    CinemaService cinemaService;
    final
    HallService hallService;
    final
    RelatedFilmsHallsEntityService relatedFilmsHallsEntityService;

    @Autowired
    public CinemasController(CinemaService cinemaService, HallService hallService, RelatedFilmsHallsEntityService relatedFilmsHallsEntityService) {
        this.cinemaService = cinemaService;
        this.hallService = hallService;
        this.relatedFilmsHallsEntityService = relatedFilmsHallsEntityService;
    }

    @GetMapping("/cinemas")
    public String stock(Model model) {
        model.addAttribute("cinemas", cinemaService.findAll());
        return "/public/cinemas";
    }

    @GetMapping("/cinemas/{name}/{hall}")
    public String hallShow(@PathVariable Integer hall, @PathVariable String name, Model model) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Optional<HallEntity> hallEntity = hallService.findById(hall);
        model.addAttribute("info", hallEntity.get());

        List<String> carousel = new ArrayList<>();
        HallEntity hallEntityCarousel = hallEntity.get();
        Optional<CinemaEntity> cinemaEntity = cinemaService.findByName(name);

        for (int i = 1; i < 5; i++) {
            Method getImgMethod = hallEntityCarousel.getClass().getMethod("getImg" + i);
            if (getImgMethod.invoke(hallEntityCarousel) != null) {
                carousel.add((String) getImgMethod.invoke(hallEntityCarousel));
                log.warn((String) getImgMethod.invoke(hallEntityCarousel));
            }
        }
        log.info("end for");
        log.info(carousel);
        model.addAttribute("sessions", relatedFilmsHallsEntityService.findAll().stream().filter(
                        relatedFilmsHallsEntity ->
                                relatedFilmsHallsEntity
                                        .getHall()
                                        .getCinemaEntity()
                                        .getIdCinema() == cinemaEntity.get()
                                        .getIdCinema()
                                        &&relatedFilmsHallsEntity.getHall().getIdHall()==hall)
                .collect(Collectors.toList()));
        model.addAttribute("carouselItems", carousel);
        return "/public/hallShow";
    }


    @GetMapping("/cinemas/{name}")
    public String cinemaShow(@PathVariable String name, Model model) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Optional<CinemaEntity> cinemaEntity = cinemaService.findByName(name);


        model.addAttribute("info", cinemaEntity.get());
        model.addAttribute("halls", hallService.findByCinemaId(cinemaEntity.get().getIdCinema()));
        model.addAttribute("sessions", relatedFilmsHallsEntityService.findAll().stream().filter(
                relatedFilmsHallsEntity ->
                        relatedFilmsHallsEntity
                                .getHall()
                                .getCinemaEntity()
                                .getIdCinema() == cinemaEntity.get()
                                .getIdCinema())
                .collect(Collectors.toList()));

        List<String> carousel = new ArrayList<>();
        CinemaEntity cinemaEntityCarousel = cinemaEntity.get();

        for (int i = 1; i < 5; i++) {
            Method getImgMethod = cinemaEntityCarousel.getClass().getMethod("getImg" + i);
            if (getImgMethod.invoke(cinemaEntityCarousel) != null) {
                carousel.add((String) getImgMethod.invoke(cinemaEntityCarousel));
                log.warn((String) getImgMethod.invoke(cinemaEntityCarousel));
            }
        }
        log.info("end for");
        log.info(carousel);

        model.addAttribute("carouselItems", carousel);

        return "/public/cinemaShow";
    }
}
