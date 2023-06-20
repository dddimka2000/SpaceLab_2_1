package org.example.controller.publicControllers;

import lombok.extern.log4j.Log4j2;
import org.example.model.FilmEntity;
import org.example.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/poster")
@Log4j2
public class PosterController {
    final
    FilmService filmService;

    @Autowired
    public PosterController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public String showFilmsPoster(Model model) {
        List<FilmEntity> filmEntities = filmService.findAll();
        model.addAttribute("now", filmEntities.stream().filter(filmEntity -> filmEntity.getIsUpcoming() == true).collect(Collectors.toList()));
        model.addAttribute("soon", filmEntities.stream().filter(filmEntity -> filmEntity.getIsUpcoming() == false).collect(Collectors.toList()));
        return "/public/poster";
    }

    @GetMapping("/{name}")
    public String showFilm(Model model, @PathVariable String name) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Optional<FilmEntity> filmEntityOptional = filmService.findByName(name);
        log.info(filmEntityOptional.get().getLinkTrailer());
        model.addAttribute("info", filmEntityOptional.get());
        List<String> carousel = new ArrayList<>();
        FilmEntity filmEntity = filmEntityOptional.get();

        for (int i = 1; i < 5; i++) {
            Method getImgMethod = filmEntity.getClass().getMethod("getImg" + i);
            if (getImgMethod.invoke(filmEntity) != null) {
                carousel.add((String) getImgMethod.invoke(filmEntity));
                log.warn((String) getImgMethod.invoke(filmEntity));
            }
        }
        log.info("end for");
        log.info(carousel);
        model.addAttribute("carouselItems", carousel);
        return "/public/showFilm";
    }
}
