package org.example.controller.adminControllers;

import lombok.extern.log4j.Log4j2;
import org.example.model.NewsEntity;
import org.example.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/admin/news")
@Log4j2
public class NewsAdminController {
    String path = "C:\\Users\\User\\IdeaProjects\\SpaceLab_2_1\\src\\main\\resources\\static\\img\\";

    final
    NewsService newsService;

    @Autowired
    public NewsAdminController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public String showNews(Model model) {
        List<NewsEntity> newsEntities = newsService.findAll();
        model.addAttribute("news", newsEntities);
        return "/admin/news/news";
    }

    @GetMapping("/{id}")
    public String showModelNews(@PathVariable Integer id, Model model) {
        NewsEntity newsEntity = newsService.findById(id).get();
        model.addAttribute("info", newsEntity);
        return "/admin/news/editNews";
    }

    @PostMapping("/{id}")
    public String saveModelNews(@PathVariable Integer id,
                                @RequestParam("name") String name,
                                @RequestParam("description") String description,
                                @RequestParam("link") String link,
                                @RequestParam("url") String url,
                                @RequestParam("title") String title,
                                @RequestParam("keywords") String keywords,
                                @RequestParam("descriptionSeo") String descriptionSeo,
                                @RequestParam(name = "mainImagine", required = false) MultipartFile mainImagine,
                                @RequestParam(name = "images[]", required = false) List<MultipartFile> images,
                                @RequestParam(name = "switchValue", required = false) Optional<Boolean> switchValue,
                                @RequestParam(name = "selectedDate",required = false) Optional<String> selectedDate) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        NewsEntity newsEntity = newsService.findById(id).get();
        newsEntity.setName(name);
        newsEntity.setDescription(description);
        newsEntity.setLink(link);
        newsEntity.setUrl(url);
        newsEntity.setTitle(title);
        newsEntity.setDescriptionSeo(descriptionSeo);
        newsEntity.setKeywords(keywords);
        newsEntity.setStatus(switchValue.isPresent());
        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + mainImagine.getOriginalFilename();
        try {
            mainImagine.transferTo(new File(path + resultFilename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String mainImaginePath = "/photos/" + resultFilename;

        newsEntity.setImg(mainImaginePath);

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
                setImgMethod = newsEntity.getClass().getMethod("setImg" + setImgNum, String.class);
                setImgMethod.invoke(newsEntity, img);
            }
            setImgNum++;
        }
        log.info(selectedDate);
        if (selectedDate.isPresent()&& !selectedDate.get().isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date parsedDate = dateFormat.parse(selectedDate.get());
                newsEntity.setDate(parsedDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        newsService.save(newsEntity);


        return "redirect:/admin/news";
    }

    @GetMapping("/createNews")
    public String createNews() {
        return "/admin/news/newNews";
    }


    @PostMapping("/createNews")
    public String createNewsPost(@RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam("link") String link,
                                 @RequestParam("url") String url,
                                 @RequestParam("title") String title,
                                 @RequestParam("keywords") String keywords,
                                 @RequestParam("descriptionSeo") String descriptionSeo,
                                 @RequestParam(name = "mainImagine", required = false) MultipartFile mainImagine,
                                 @RequestParam(name = "images[]", required = false) List<MultipartFile> images,
                                 @RequestParam(name = "switchValue", required = false) Optional<Boolean> switchValue,
                                 @RequestParam(name = "selectedDate") Optional<String> selectedDate)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setName(name);
        newsEntity.setDescription(description);
        newsEntity.setLink(link);
        newsEntity.setUrl(url);
        newsEntity.setTitle(title);
        newsEntity.setDescriptionSeo(descriptionSeo);
        newsEntity.setKeywords(keywords);
        newsEntity.setStatus(switchValue.isPresent());


        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + mainImagine.getOriginalFilename();
        try {
            mainImagine.transferTo(new File(path + resultFilename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String mainImaginePath = "/photos/" + resultFilename;

        newsEntity.setImg(mainImaginePath);

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
                setImgMethod = newsEntity.getClass().getMethod("setImg" + setImgNum, String.class);
                setImgMethod.invoke(newsEntity, img);
            }
            setImgNum++;
        }
        log.info(selectedDate);
        if (selectedDate.isPresent()&& !selectedDate.get().isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date parsedDate = dateFormat.parse(selectedDate.get());
                newsEntity.setDate(parsedDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            newsEntity.setDate(new Date());
        }
        newsService.save(newsEntity);
        return "redirect:/admin/news";
    }

    @PostMapping("/delete/{id}")
    public String deleteNews(@PathVariable Integer id){
        newsService.delete(newsService.findById(id).get());
        return "redirect:/admin/news";
    }
}
