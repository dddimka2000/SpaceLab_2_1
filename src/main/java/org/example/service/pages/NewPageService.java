package org.example.service.pages;

import org.example.model.PageEntity;
import org.example.repository.pages.NewPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewPageService {
    private final
    NewPageRepository newPageRepository;

    @Autowired
    public NewPageService(NewPageRepository newPageRepository) {
        this.newPageRepository = newPageRepository;
    }

    public PageEntity save(PageEntity pageEntity){
        return newPageRepository.save(pageEntity);
    }

    public Optional<PageEntity> findById(Integer id) {
        Optional<PageEntity> mainPage=newPageRepository.findById(id);
        return mainPage;
    }
    public List<PageEntity> findAllPages(){
        return newPageRepository.findAll();
    }

    public Optional<PageEntity> findByName(String name){
        return newPageRepository.findByName(name);
    }
    public  void delete(PageEntity entity){
        newPageRepository.delete(entity);
    };

}
