package org.example.service.pages;

import lombok.extern.log4j.Log4j2;
import org.example.model.PageEntity;
import org.example.repository.pages.NewPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Log4j2
@Service
public class NewPageService {
    private final
    NewPageRepository newPageRepository;

    @Autowired
    public NewPageService(NewPageRepository newPageRepository) {
        this.newPageRepository = newPageRepository;
    }

    public PageEntity save(PageEntity pageEntity){

        log.info(pageEntity + " has been saved");
        return newPageRepository.save(pageEntity);
    }

    public Optional<PageEntity> findById(Integer id) {
        Optional<PageEntity> mainPage=newPageRepository.findById(id);
        log.info("PageEntity with id " + id + " has been found");
        return mainPage;
    }
    public List<PageEntity> findAllPages(){
        log.info("All Pages have been found");
        return newPageRepository.findAll();
    }

    public Optional<PageEntity> findByName(String name){
        log.info("Page with name " + name + " has been found");
        return newPageRepository.findByName(name);
    }
    public  void delete(PageEntity entity){
        log.info("PageEntity with id" + entity.getIdPage() + " has been removed");
        newPageRepository.delete(entity);
    };

}
