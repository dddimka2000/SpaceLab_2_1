package org.example.service.pages;

import org.example.model.PageEntity;
import org.example.repository.pages.NewPageRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

class NewPageServiceTest {
    @Mock
    private NewPageRepository newPageRepository;

    @InjectMocks
    private NewPageService newPageService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        PageEntity pageEntity = new PageEntity();
        when(newPageRepository.save(pageEntity)).thenReturn(pageEntity);

        PageEntity savedEntity = newPageService.save(pageEntity);

        Assertions.assertEquals(pageEntity, savedEntity);
        verify(newPageRepository, times(1)).save(pageEntity);
    }

    @Test
    void findById() {
        Integer pageId = 1;
        PageEntity pageEntity = new PageEntity();
        when(newPageRepository.findById(pageId)).thenReturn(Optional.of(pageEntity));

        Optional<PageEntity> foundEntity = newPageService.findById(pageId);

        Assertions.assertEquals(Optional.of(pageEntity), foundEntity);
        verify(newPageRepository, times(1)).findById(pageId);
    }

    @Test
    void findAllPages() {
        List<PageEntity> pages = new ArrayList<>();
        when(newPageRepository.findAll()).thenReturn(pages);

        List<PageEntity> foundPages = newPageService.findAllPages();

        Assertions.assertEquals(pages, foundPages);
        verify(newPageRepository, times(1)).findAll();
    }

    @Test
    void findByName() {
        String pageName = "TestPage";
        PageEntity pageEntity = new PageEntity();
        when(newPageRepository.findByName(pageName)).thenReturn(Optional.of(pageEntity));

        Optional<PageEntity> foundEntity = newPageService.findByName(pageName);

        Assertions.assertEquals(Optional.of(pageEntity), foundEntity);
        verify(newPageRepository, times(1)).findByName(pageName);
    }

    @Test
    void delete() {
        PageEntity pageEntity = new PageEntity();

        newPageService.delete(pageEntity);

        verify(newPageRepository, times(1)).delete(pageEntity);
    }
}