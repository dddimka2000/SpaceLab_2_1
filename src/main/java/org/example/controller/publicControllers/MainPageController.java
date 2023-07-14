package org.example.controller.publicControllers;

import lombok.extern.log4j.Log4j2;
import org.example.controller.modelPage.MainPageModel;
import org.example.model.FilmEntity;
import org.example.model.ImagineEntity;
import org.example.model.MainPageEntity;
import org.example.repository.pages.MainPageRepository;
import org.example.security.UserDetailsImpl;
import org.example.service.FilmService;
import org.example.service.ImagineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Controller
public class MainPageController {
    final
    MainPageRepository mainPageRepository;
    final
    FilmService filmService;


    @Autowired
    public MainPageController(MainPageRepository mainPageRepository, ImagineService imagineService, FilmService filmService) {
        this.mainPageRepository = mainPageRepository;
        this.imagineService = imagineService;
        this.filmService = filmService;
    }

    final
    ImagineService imagineService;

    @GetMapping("/")
    public String main(Model model) {
        String logUser;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl personDetails = (UserDetailsImpl) authentication.getPrincipal();
            logUser = personDetails.getUserEntity().getLog();

        } catch (Exception e) {
            logUser="";
        }


        List<ImagineEntity> imagineEntities = imagineService.findByName("Главная страница");
        List<ImagineEntity> carouselItems = imagineService.findByName("Карусель");
        List<ImagineEntity> newsAndStocks = imagineService.findByName("НовостиАкции");
        List<FilmEntity> filmEntities = filmService.findAll();
        MainPageModel mainModel = new MainPageModel();
        mainModel.setLogUser(logUser);
        if (!imagineEntities.isEmpty()) {
            String imagePath = imagineEntities.get(0).getPath();
            log.info("path= " + imagePath);
            mainModel.setImagePath(imagePath);
        }
        mainModel.setBackgroundSelection(imagineEntities.get(0).getBackgroundSelection());
        model.addAttribute("carouselItems", carouselItems);
        model.addAttribute("mainModel", mainModel);
        model.addAttribute("newsAndStocks", newsAndStocks);
        model.addAttribute("filmsNow", filmEntities.stream().filter(filmEntity -> filmEntity.getIsUpcoming() == true).collect(Collectors.toList()));
        model.addAttribute("filmsSoon", filmEntities.stream().filter(filmEntity -> filmEntity.getIsUpcoming() == false).collect(Collectors.toList()));
        Optional<MainPageEntity> mainPage = mainPageRepository.findById(1);
        model.addAttribute("mainPage", mainPage.get());
        return "public/main";

    }

}