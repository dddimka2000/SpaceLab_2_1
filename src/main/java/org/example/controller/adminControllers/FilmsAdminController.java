package org.example.controller.adminControllers;

import lombok.extern.log4j.Log4j2;
import org.example.model.FilmEntity;
import org.example.service.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@Log4j2
@RequestMapping("/admin/films")
public class FilmsAdminController {
    String path = "C:\\Users\\User\\IdeaProjects\\SpaceLab_2_1\\src\\main\\resources\\static\\img\\";
    private final
    FilmService filmService;

    public FilmsAdminController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/edit/{idFilm}")
    public String editFilmAdmin(@PathVariable("idFilm") Integer integer, Model model) {
        Optional<FilmEntity> filmEntity = filmService.findById(integer);
        model.addAttribute("info", filmEntity.get());

        return "admin/films/editFilm";
    }

    @GetMapping
    public String filmsAdmin(Model model) {
        List<FilmEntity> listFilms = filmService.findAll();
        model.addAttribute("listFilms", listFilms);
        return "admin/films/films";
    }

    @GetMapping("/newFilm")
    public String newFilmAdmin() {
        return "admin/films/newFilm";
    }

    @PostMapping("/newFilm/edit/{idFilm}")
    public String newFilmAdminPost(@PathVariable("idFilm") Integer integer,
                                   @RequestParam("nameFilm") String nameFilm,
                                   @RequestParam("description") String description,
                                   @RequestParam("linkTrailer") String linkTrailer,
                                   @RequestParam("cinemaType") String cinemaType,
                                   @RequestParam("url") String url,
                                   @RequestParam("title") String title,
                                   @RequestParam("keywords") String keywords,
                                   @RequestParam("descriptionSeo") String descriptionSeo,
                                   @RequestParam(name = "mainImagine", required = false) MultipartFile mainImagine,
                                   @RequestParam(name = "images[]", required = false) List<MultipartFile> images,
                                   @RequestParam(name = "isUpcoming",required = false) Boolean isUpcoming)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        FilmEntity filmEntity = filmService.findById(integer).get();
        filmEntity.setName(nameFilm);
        filmEntity.setDescription(description);
        filmEntity.setLinkTrailer(linkTrailer);
        filmEntity.setType(cinemaType);
        filmEntity.setUrl(url);
        filmEntity.setTitle(title);
        filmEntity.setDescriptionSeo(descriptionSeo);
        filmEntity.setKeywords(keywords);
        filmEntity.setIsUpcoming(isUpcoming);

        String uuidFile;
        String resultFilename;
        if (mainImagine != null && !mainImagine.isEmpty()) {
            uuidFile = UUID.randomUUID().toString();
            resultFilename = uuidFile + "." + mainImagine.getOriginalFilename();
            try {
                mainImagine.transferTo(new File(path + resultFilename));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String mainImaginePath = "/photos/" + resultFilename;


            filmEntity.setImg(mainImaginePath);
        }

        String img;
        Method setImgMethod;
        int setImgNum = 1;
        for (MultipartFile file : images) {
            if (file != null && !file.isEmpty()) {
                uuidFile = UUID.randomUUID().toString();
                resultFilename = uuidFile + "." + file.getOriginalFilename();
                try {
                    file.transferTo(new File(path + resultFilename));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                img = "/photos/" + resultFilename;
                setImgMethod = filmEntity.getClass().getMethod("setImg" + setImgNum, String.class);
                setImgMethod.invoke(filmEntity, img);
            }
            setImgNum++;
        }

        filmService.save(filmEntity);

        return "redirect:/admin/films";
    }


    @PostMapping("/newFilm/create")
    public String newFilmAdminPost(@RequestParam("nameFilm") String nameFilm,
                                   @RequestParam("description") String description,
                                   @RequestParam("linkTrailer") String linkTrailer,
                                   @RequestParam("cinemaType") String cinemaType,
                                   @RequestParam("url") String url,
                                   @RequestParam("title") String title,
                                   @RequestParam("keywords") String keywords,
                                   @RequestParam("descriptionSeo") String descriptionSeo,
                                   @RequestParam(name = "mainImagine", required = false) MultipartFile mainImagine,
                                   @RequestParam(name = "images[]", required = false) List<MultipartFile> images,
                                   @RequestParam(name = "isUpcoming",required = false) Boolean isUpcoming)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setName(nameFilm);
        filmEntity.setDescription(description);
        filmEntity.setLinkTrailer(linkTrailer);
        filmEntity.setType(cinemaType);
        filmEntity.setUrl(url);
        filmEntity.setTitle(title);
        filmEntity.setDescriptionSeo(descriptionSeo);
        filmEntity.setKeywords(keywords);
        filmEntity.setIsUpcoming(isUpcoming);


        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + mainImagine.getOriginalFilename();
        try {
            mainImagine.transferTo(new File(path + resultFilename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String mainImaginePath = "/photos/" + resultFilename;

        filmEntity.setImg(mainImaginePath);

        String img;
        Method setImgMethod;
        int setImgNum = 1;
        for (MultipartFile file : images) {
            if (file != null && !file.isEmpty()) {
                uuidFile = UUID.randomUUID().toString();
                resultFilename = uuidFile + "." + file.getOriginalFilename();
                try {
                    file.transferTo(new File(path + resultFilename));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                img = "/photos/" + resultFilename;
                setImgMethod = filmEntity.getClass().getMethod("setImg" + setImgNum, String.class);
                setImgMethod.invoke(filmEntity, img);
            }
            setImgNum++;
        }


        filmService.save(filmEntity);

        return "redirect:/admin/films";
    }
}
