package org.example.service;

import org.example.model.NewsEntity;
import org.example.repository.NewsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class NewsServiceTest {
    @Mock
    private NewsRepository newsRepository;

    @InjectMocks
    private NewsService newsService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void findAll() {
        List<NewsEntity> newsList = new ArrayList<>();
        newsList.add(new NewsEntity());
        newsList.add(new NewsEntity());

        when(newsRepository.findAll()).thenReturn(newsList);

        List<NewsEntity> result = newsService.findAll();

        assertNotNull(result);
        assertEquals(newsList, result);
        verify(newsRepository).findAll();
    }

    @Test
    void save() {
        NewsEntity newsEntity = new NewsEntity();

        newsService.save(newsEntity);

        verify(newsRepository).save(newsEntity);
    }

    @Test
    void findById() {
        Integer newsId = 1;
        NewsEntity newsEntity = new NewsEntity();

        when(newsRepository.findById(newsId)).thenReturn(Optional.of(newsEntity));

        Optional<NewsEntity> result = newsService.findById(newsId);

        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(newsEntity, result.get());
        verify(newsRepository).findById(newsId);
    }

    @Test
    void delete() {
        NewsEntity newsEntity = new NewsEntity();

        newsService.delete(newsEntity);

        verify(newsRepository).delete(newsEntity);
    }

    @Test
    void findAllPageByStatus() {
        boolean status = true;
        int pageNumber = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<NewsEntity> newsList = new ArrayList<>();
        newsList.add(new NewsEntity());
        newsList.add(new NewsEntity());
        Page<NewsEntity> expectedPage = new PageImpl<>(newsList, pageable, newsList.size());

        when(newsRepository.findByStatus(status, pageable)).thenReturn(expectedPage);

        Page<NewsEntity> resultPage = newsService.findAllPageByStatus(status, pageNumber, pageSize);

        assertNotNull(resultPage);
        assertEquals(expectedPage, resultPage);
        verify(newsRepository).findByStatus(status, pageable);
    }
}