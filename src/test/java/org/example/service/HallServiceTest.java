package org.example.service;

import org.example.model.HallEntity;
import org.example.repository.HallRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
class HallServiceTest {
    @Mock
    private HallRepository hallRepository;

    @InjectMocks
    private HallService hallService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findById() {
        Integer hallId = 1;
        HallEntity hallEntity = new HallEntity();
        when(hallRepository.findById(hallId)).thenReturn(Optional.of(hallEntity));

        Optional<HallEntity> foundEntity = hallService.findById(hallId);

        Assertions.assertEquals(Optional.of(hallEntity), foundEntity);
        verify(hallRepository, times(1)).findById(hallId);

    }

    @Test
    void save() {
        HallEntity hallEntity = new HallEntity();
        when(hallRepository.save(hallEntity)).thenReturn(hallEntity);

        HallEntity savedEntity = hallService.save(hallEntity);

        Assertions.assertEquals(hallEntity, savedEntity);
        verify(hallRepository, times(1)).save(hallEntity);
    }

    @Test
    void findByEmptyCinemaId() {
        List<HallEntity> halls = new ArrayList<>();
        when(hallRepository.findByCinemaEntityNull()).thenReturn(halls);

        List<HallEntity> foundHalls = hallService.findByEmptyCinemaId();

        Assertions.assertEquals(halls, foundHalls);
        verify(hallRepository, times(1)).findByCinemaEntityNull();
    }

    @Test
    void findByCinemaId() {
        Integer cinemaId = 1;
        List<HallEntity> halls = new ArrayList<>();
        when(hallRepository.findByCinemaEntityIdCinema(cinemaId)).thenReturn(halls);

        List<HallEntity> foundHalls = hallService.findByCinemaId(cinemaId);

        Assertions.assertEquals(halls, foundHalls);
        verify(hallRepository, times(1)).findByCinemaEntityIdCinema(cinemaId);
    }

    @Test
    void remove() {
        Integer hallId = 1;

        hallService.remove(hallId);

        verify(hallRepository, times(1)).deleteById(hallId);
    }
}