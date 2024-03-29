package org.example.service;

import org.example.model.NewsEntity;
import org.example.model.StockEntity;
import org.example.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    final
    NewsRepository newsRepository;

    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }


    public List<NewsEntity> findAll() {
        return newsRepository.findAll();
    }


    public <S extends NewsEntity> S save(S entity) {
        return newsRepository.save(entity);
    }

    public Optional<NewsEntity> findById(Integer integer) {
        return newsRepository.findById(integer);
    }


    public void delete(NewsEntity entity) {
        newsRepository.delete(entity);
    }


}
