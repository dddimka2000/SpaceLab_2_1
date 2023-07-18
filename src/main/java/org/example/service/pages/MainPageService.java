package org.example.service.pages;

import lombok.extern.log4j.Log4j2;
import org.example.model.MainPageEntity;
import org.example.repository.pages.MainPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class MainPageService {
    final
    MainPageRepository mainPageRepository;
    @Autowired
    public MainPageService(MainPageRepository mainPageRepository) {
        this.mainPageRepository = mainPageRepository;
    }
    public MainPageEntity saveMainPage(MainPageEntity mainPage) {
        log.info(mainPage + " has been saved");

        return mainPageRepository.save(mainPage);
    }
    public Optional<MainPageEntity> findById(Integer id) {
        Optional<MainPageEntity> mainPage=mainPageRepository.findFirstByIdMainPage(id);
        log.info("MainPageEntity with id " + id + " has been found");
        return mainPage;
    }
    public Optional<MainPageEntity> findFirstByNamePage(String name){
        Optional<MainPageEntity> mainPage=mainPageRepository.findFirstByNamePage(name);
        log.info("MainPageEntity with name " + name + " has been found");
        return mainPage;
    }


}
