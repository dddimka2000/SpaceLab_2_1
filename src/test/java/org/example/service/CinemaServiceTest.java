package org.example.service;

import org.example.model.CinemaEntity;
import org.example.repository.CinemaRepository;
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
class CinemaServiceTest {
    @Mock
    private CinemaRepository cinemaRepository;

    @InjectMocks
    private CinemaService cinemaService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void save() {
        CinemaEntity cinemaEntity = new CinemaEntity();
        when(cinemaRepository.save(cinemaEntity)).thenReturn(cinemaEntity);

        CinemaEntity savedEntity = cinemaService.save(cinemaEntity);

        Assertions.assertEquals(cinemaEntity, savedEntity);
        verify(cinemaRepository, times(1)).save(cinemaEntity);
    }

    @Test
    void findById() {
        Integer cinemaId = 1;
        CinemaEntity cinemaEntity = new CinemaEntity();
        when(cinemaRepository.findByIdCinema(cinemaId)).thenReturn(Optional.of(cinemaEntity));

        Optional<CinemaEntity> foundEntity = cinemaService.findById(cinemaId);

        Assertions.assertEquals(Optional.of(cinemaEntity), foundEntity);
        verify(cinemaRepository, times(1)).findByIdCinema(cinemaId);
    }

    @Test
    void findAll() {
        // Arrange
        List<CinemaEntity> cinemas = new ArrayList<>();
        when(cinemaRepository.findAll()).thenReturn(cinemas);


        List<CinemaEntity> foundCinemas = cinemaService.findAll();

        Assertions.assertEquals(cinemas, foundCinemas);
        verify(cinemaRepository, times(1)).findAll();
    }

    @Test
    void findLast() {
        CinemaEntity cinemaEntity = new CinemaEntity();
        when(cinemaRepository.findTopByOrderByIdCinemaDesc()).thenReturn(Optional.of(cinemaEntity));

        Optional<CinemaEntity> foundEntity = cinemaService.findLast();

        Assertions.assertEquals(Optional.of(cinemaEntity), foundEntity);
        verify(cinemaRepository, times(1)).findTopByOrderByIdCinemaDesc();
    }

    @Test
    void remove() {
        Integer cinemaId = 1;

        cinemaService.remove(cinemaId);

        verify(cinemaRepository, times(1)).deleteById(cinemaId);
    }

    @Test
    void findByName() {
        String cinemaName = "TestCinema";
        CinemaEntity cinemaEntity = new CinemaEntity();
        when(cinemaRepository.findByName(cinemaName)).thenReturn(Optional.of(cinemaEntity));

        Optional<CinemaEntity> foundEntity = cinemaService.findByName(cinemaName);

        Assertions.assertEquals(Optional.of(cinemaEntity), foundEntity);
        verify(cinemaRepository, times(1)).findByName(cinemaName);
    }
}