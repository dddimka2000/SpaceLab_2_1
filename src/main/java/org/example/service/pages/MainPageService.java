package org.example.service.pages;

import org.example.model.MainPageEntity;
import org.example.repository.pages.MainPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MainPageService {
    final
    MainPageRepository mainPageRepository;
    @Autowired
    public MainPageService(MainPageRepository mainPageRepository) {
        this.mainPageRepository = mainPageRepository;
    }
    public MainPageEntity saveMainPage(MainPageEntity mainPage) {
        return mainPageRepository.save(mainPage);
    }
    public Optional<MainPageEntity> findById(Integer id) {
        Optional<MainPageEntity> mainPage=mainPageRepository.findFirstByIdMainPage(id);
        return mainPage;
    }
    public Optional<MainPageEntity> findFirstByNamePage(String name){
        Optional<MainPageEntity> mainPage=mainPageRepository.findFirstByNamePage(name);
        return mainPage;
    }


}
