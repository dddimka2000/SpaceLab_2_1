package org.example.service;

import org.example.model.RelatedFilmsHallsEntity;
import org.example.repository.RelatedFilmsHallsEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RelatedFilmsHallsEntityServiceTest {

    @Mock
    private RelatedFilmsHallsEntityRepository relatedFilmsHallsEntityRepository;

    @InjectMocks
    private RelatedFilmsHallsEntityService relatedFilmsHallsEntityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        List<RelatedFilmsHallsEntity> relatedFilmsHallsList = new ArrayList<>();
        relatedFilmsHallsList.add(new RelatedFilmsHallsEntity());
        relatedFilmsHallsList.add(new RelatedFilmsHallsEntity());

        when(relatedFilmsHallsEntityRepository.findAll()).thenReturn(relatedFilmsHallsList);

        List<RelatedFilmsHallsEntity> result = relatedFilmsHallsEntityService.findAll();

        assertNotNull(result);
        assertEquals(relatedFilmsHallsList, result);
        verify(relatedFilmsHallsEntityRepository).findAll();
    }

    @Test
    void save() {
        RelatedFilmsHallsEntity relatedFilmsHallsEntity = new RelatedFilmsHallsEntity();

        relatedFilmsHallsEntityService.save(relatedFilmsHallsEntity);

        verify(relatedFilmsHallsEntityRepository).save(relatedFilmsHallsEntity);
    }


    @Test
    void findById() {
        Integer entityId = 1;
        when(relatedFilmsHallsEntityRepository.findById(entityId)).thenReturn(Optional.empty());

        Optional<RelatedFilmsHallsEntity> result = relatedFilmsHallsEntityService.findById(entityId);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(relatedFilmsHallsEntityRepository).findById(entityId);
    }

    @Test
    void delete() {
        RelatedFilmsHallsEntity relatedFilmsHallsEntity = new RelatedFilmsHallsEntity();

        relatedFilmsHallsEntityService.delete(relatedFilmsHallsEntity);

        verify(relatedFilmsHallsEntityRepository).delete(relatedFilmsHallsEntity);
    }
}