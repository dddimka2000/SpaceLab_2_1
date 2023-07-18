package org.example.service.pages;

import org.example.model.MainPageEntity;
import org.example.repository.pages.MainPageRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
class MainPageServiceTest {
    @Mock
    private MainPageRepository mainPageRepository;

    @InjectMocks
    private MainPageService mainPageService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void saveMainPage() {
        MainPageEntity mainPageEntity = new MainPageEntity();
        when(mainPageRepository.save(mainPageEntity)).thenReturn(mainPageEntity);

        MainPageEntity savedEntity = mainPageService.saveMainPage(mainPageEntity);

        Assertions.assertEquals(mainPageEntity, savedEntity);
        verify(mainPageRepository, times(1)).save(mainPageEntity);
    }

    @Test
    void findById() {
        Integer mainPageId = 1;
        MainPageEntity mainPageEntity = new MainPageEntity();
        when(mainPageRepository.findFirstByIdMainPage(mainPageId)).thenReturn(Optional.of(mainPageEntity));

        Optional<MainPageEntity> foundEntity = mainPageService.findById(mainPageId);

        Assertions.assertEquals(Optional.of(mainPageEntity), foundEntity);
        verify(mainPageRepository, times(1)).findFirstByIdMainPage(mainPageId);
    }

    @Test
    void findFirstByNamePage() {
        String pageName = "TestPage";
        MainPageEntity mainPageEntity = new MainPageEntity();
        when(mainPageRepository.findFirstByNamePage(pageName)).thenReturn(Optional.of(mainPageEntity));

        Optional<MainPageEntity> foundEntity = mainPageService.findFirstByNamePage(pageName);

        Assertions.assertEquals(Optional.of(mainPageEntity), foundEntity);
        verify(mainPageRepository, times(1)).findFirstByNamePage(pageName);

    }
}