package org.example.controller.adminControllers.pages;

import lombok.extern.log4j.Log4j2;
import org.example.model.MainPageEntity;
import org.example.service.pages.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.util.Optional;

@Log4j2
@Controller
@RequestMapping("/admin/pages/main")
public class PublicMainPageController {
    private final MainPageService mainPageService;

    @Autowired
    public PublicMainPageController(MainPageService mainPageService) {
        this.mainPageService = mainPageService;
    }

    @GetMapping
    public String publicMainPageAdmin(Model model) throws FileNotFoundException {
        Optional<MainPageEntity> mainPage= mainPageService.findById(1);
        if(!mainPage.isPresent()){
            log.error("EMPTY!");
        }
        log.info(mainPage.get());
        model.addAttribute("mainPage", mainPage.get());
        StringBuilder description = new StringBuilder();
        StringBuilder seoText = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/static/files/mainPage/description.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                description.append(line).append("\n");
            }
        } catch (IOException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/static/files/mainPage/seoText.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                seoText.append(line).append("\n");
            }
        } catch (IOException e) {
            log.error(e);
            throw new RuntimeException(e);
        }

        model.addAttribute("seoText",seoText);
        model.addAttribute("description",description);
        return "/admin/pages/publicMain";
    }

    @PostMapping
    public String postMainPageAdmin(
            @RequestParam("telephone1") String telephone1,
            @RequestParam("telephone2") String telephone2,
            @RequestParam("seoText") String seoText,
            @RequestParam("url") String url,
            @RequestParam("title") String title,
            @RequestParam("keywords") String keywords,
            @RequestParam("description") String description
    ) {
        Optional<MainPageEntity> mainPage=mainPageService.findById(1);
        mainPage.get().setTelephone1(telephone1);
        mainPage.get().setTelephone2(telephone2);
        mainPage.get().setUrl(url);
        mainPage.get().setTitle(title);
        mainPage.get().setKeywords(keywords);
        mainPageService.saveMainPage(mainPage.get());
        try {
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("src/main/resources/static/files/mainPage/description.txt"));
            bufferedWriter.write(description);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("src/main/resources/static/files/mainPage/seoText.txt"));
            bufferedWriter.write(seoText);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/admin/pages/main";
    }
}
