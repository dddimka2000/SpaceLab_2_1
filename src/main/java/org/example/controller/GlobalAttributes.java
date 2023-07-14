package org.example.controller;

import org.example.model.MainPageEntity;
import org.example.model.PageEntity;
import org.example.security.UserDetailsImpl;
import org.example.service.pages.MainPageService;
import org.example.service.pages.NewPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalAttributes {
    final
    NewPageService newPageService;
    final
    MainPageService mainPageService;

    @Autowired
    public GlobalAttributes(NewPageService newPageService, MainPageService mainPageService) {
        this.newPageService = newPageService;
        this.mainPageService = mainPageService;
    }

    @ModelAttribute("mainPage")
    public MainPageEntity getValueFromDatabaseMainPage() {
        return mainPageService.findFirstByNamePage("Главная страница").get();
    }

    @ModelAttribute("contactsPage")
    public MainPageEntity getValueFromDatabaseContacts() {
        return mainPageService.findFirstByNamePage("Контакты").get()
       ;

    }

    @ModelAttribute("otherPages")
    public List<PageEntity> getValueFromDatabaseOtherPages() {
        return newPageService.findAllPages().stream().limit(5).filter(pageEntity -> pageEntity.getStatus() == true).collect(Collectors.toList());
    }

    @ModelAttribute("newPages")
    public List<PageEntity> getValueFromDatabaseNewPages() {
        return newPageService.findAllPages().stream().skip(5).filter(pageEntity -> pageEntity.getStatus() == true).collect(Collectors.toList());
    }
    @ModelAttribute("nameUser")
    public Optional<String> getValueNameUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            Optional<String> nameUser = Optional.ofNullable(userDetails.getUsername());
            return nameUser;
        }
        return Optional.empty();
    }
}
