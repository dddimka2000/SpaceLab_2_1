package org.example.controller.adminControllers.pages;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.example.model.PageEntity;
import org.example.service.pages.MainPageService;
import org.example.service.pages.NewPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/pages")
public class AdminPagesController {
    private final
    MainPageService mainPageService;
    final
    NewPageService newPageService;

    @Autowired
    public AdminPagesController(MainPageService mainPageService, NewPageService newPageService) {
        this.mainPageService = mainPageService;
        this.newPageService = newPageService;
    }

    @GetMapping
    public String pagesAdmin(Model model) {
        model.addAttribute("mainPage", mainPageService.findFirstByNamePage("Главная страница").get());
        Pageable pageable = PageRequest.of(0, 5);
        List<PageEntity> listPage=  newPageService.findAllPages();
        model.addAttribute("pages", listPage.stream().limit(5).collect(Collectors.toList()));
        model.addAttribute("newPages", listPage.stream().skip(5).collect(Collectors.toList()));

        model.addAttribute("contacts",mainPageService.findFirstByNamePage("Контакты").get());
        return "admin/pages/pages";

    }
    @PostMapping("/delete/{name}")
    public String deleteNewPageAdmin(@PathVariable String name){
        PageEntity pageEntity=newPageService.findByName(name).get();
        newPageService.delete(pageEntity);

        return "redirect:/admin/pages";
    }


}
