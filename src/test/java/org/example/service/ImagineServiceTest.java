package org.example.service;

import org.example.model.ImagineEntity;
import org.example.repository.ImagineRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

class ImagineServiceTest {

    @Mock
    private ImagineRepository imagineRepository;

    @InjectMocks
    private ImagineService imagineService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void save() {
        ImagineEntity imagineEntity = new ImagineEntity();
        when(imagineRepository.save(imagineEntity)).thenReturn(imagineEntity);

        ImagineEntity savedEntity = imagineService.save(imagineEntity);

        Assertions.assertEquals(imagineEntity, savedEntity);
        verify(imagineRepository, times(1)).save(imagineEntity);
    }

    @Test
    void findById() {
        Integer entityId = 1;
        ImagineEntity imagineEntity = new ImagineEntity();
        when(imagineRepository.findById(entityId)).thenReturn(Optional.of(imagineEntity));

        Optional<ImagineEntity> foundEntity = imagineService.findById(entityId);

        Assertions.assertEquals(Optional.of(imagineEntity), foundEntity);
        verify(imagineRepository, times(1)).findById(entityId);
    }

    @Test
    void findByName() {
        String name = "TestName";
        ArrayList<ImagineEntity> entities = new ArrayList<>();
        when(imagineRepository.findByName(name)).thenReturn(entities);

        ArrayList<ImagineEntity> foundEntities = imagineService.findByName(name);

        Assertions.assertEquals(entities, foundEntities);
        verify(imagineRepository, times(1)).findByName(name);
    }

    @Test
    void delete() {
        ImagineEntity imagineEntity = new ImagineEntity();

        imagineService.delete(imagineEntity);

        verify(imagineRepository, times(1)).delete(imagineEntity);
    }
}