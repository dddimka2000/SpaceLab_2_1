package org.example.service;

import lombok.extern.log4j.Log4j2;
import org.example.model.NewsEntity;
import org.example.model.StockEntity;
import org.example.model.UserEntity;
import org.example.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Log4j2
@Service
public class NewsService {
    final
    NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }


    public List<NewsEntity> findAll() {
        log.info("All news have been found");

        return newsRepository.findAll();
    }


    public <S extends NewsEntity> S save(S entity) {

        log.info(entity + " has been saved");
        return newsRepository.save(entity);
    }

    public Optional<NewsEntity> findById(Integer integer) {
        log.info("News with id " + integer + " has been found");

        return newsRepository.findById(integer);
    }


    public void delete(NewsEntity entity) {
        log.info("News with id" + entity.getId() + " has been removed");
        newsRepository.delete(entity);
    }

    public Page<NewsEntity> findAllPageByStatus(Boolean bool,Integer pageNumber, Integer pageSize){
        log.info("News with " + pageNumber + " has been found. Status = " + bool);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return newsRepository.findByStatus(bool, pageable);

    }


}
