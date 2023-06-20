package org.example.controller.publicControllers;

import lombok.extern.log4j.Log4j2;
import org.example.model.NewsEntity;
import org.example.model.PageEntity;
import org.example.service.NewsService;
import org.example.service.pages.ContactsCinemaService;
import org.example.service.pages.NewPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Log4j2
public class PagesController {
    final
    ContactsCinemaService contactsCinemaService;
    final
    NewsService newsService;
    final
    NewPageService newPageService;

    @Autowired
    public PagesController(NewsService newsService, NewPageService newPageService, ContactsCinemaService contactsCinemaService) {
        this.newsService = newsService;
        this.newPageService = newPageService;
        this.contactsCinemaService = contactsCinemaService;
    }

    @GetMapping("/mobileApplications")
    public String showMobileApplicationsPagePublic() {
        return "public/pages/mobileApplications";
    }

    @GetMapping("/news")
    public String showNewsPagePublic(Model model) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<String> dateList = new ArrayList<>();
        List<NewsEntity> newsEntities = newsService.findAll().stream().filter(newsEntity -> newsEntity.getStatus() == true).collect(Collectors.toList());

        newsService.findAll()
                .stream()
                .forEach(newsEntity -> {
                    String dateString = newsEntity.getDate().toString();
                    try {
                        Date date = inputFormat.parse(dateString);
                        String formattedDate = outputFormat.format(date);
                        dateList.add(formattedDate);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                });
        model.addAttribute("news", newsEntities);
        model.addAttribute("dateList", dateList);
        return "public/pages/news";
    }

    @GetMapping("/Кафе - Бар")
    public String showBarPagePublic(Model model) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        PageEntity page = newPageService.findByName("Кафе - Бар").get();
        model.addAttribute("info", page);

        List<String> carousel = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            Method getImgMethod = page.getClass().getMethod("getImg" + i);
            if (getImgMethod.invoke(page) != null) {
                carousel.add((String) getImgMethod.invoke(page));
            }
        }
        model.addAttribute("imgList", carousel);
        return "public/pages/barShow";
    }

    @GetMapping("/news/{id}")
    public String showNewsIdPagePublic(Model model, @PathVariable Integer id) {
        model.addAttribute("info", newsService.findById(id).get());
        return "public/pages/showNews";
    }

    @GetMapping("/{name}")
    public String showPagesPublic(Model model, @PathVariable String name) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        model.addAttribute("info", newPageService.findByName(name).get());

        List<String> carousel = new ArrayList<>();
        PageEntity filmEntity = newPageService.findByName(name).get();

        for (int i = 1; i < 5; i++) {
            Method getImgMethod = filmEntity.getClass().getMethod("getImg" + i);
            if (getImgMethod.invoke(filmEntity) != null) {
                carousel.add((String) getImgMethod.invoke(filmEntity));
            }
        }
        model.addAttribute("carouselItems", carousel);
        return "public/pages/showPage";
    }

    @GetMapping("/О кинотеатре")
    public String showAboutCinemaPagePublic() {
        return "public/pages/aboutCinemaShow";
    }

    @GetMapping("/Контакты")
    public String showContactsPagePublic(Model model) {
        model.addAttribute("contacts", contactsCinemaService.findAll().stream().filter(cinemaEntity -> cinemaEntity.getStatus() == true).collect(Collectors.toList()));

        return "public/pages/contactsPage";
    }


}
