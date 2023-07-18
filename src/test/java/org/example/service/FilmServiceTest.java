package org.example.service;

import org.example.model.FilmEntity;
import org.example.repository.FilmRepository;
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

class FilmServiceTest {
    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private FilmService filmService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save() {
        FilmEntity filmEntity = new FilmEntity();
        when(filmRepository.save(filmEntity)).thenReturn(filmEntity);

        FilmEntity savedEntity = filmService.save(filmEntity);

        Assertions.assertEquals(filmEntity, savedEntity);
        verify(filmRepository, times(1)).save(filmEntity);
    }

    @Test
    void findById() {
        Integer filmId = 1;
        FilmEntity filmEntity = new FilmEntity();
        when(filmRepository.findByIdFilm(filmId)).thenReturn(Optional.of(filmEntity));

        Optional<FilmEntity> foundEntity = filmService.findById(filmId);

        Assertions.assertEquals(Optional.of(filmEntity), foundEntity);
        verify(filmRepository, times(1)).findByIdFilm(filmId);
    }

    @Test
    void findAll() {
        List<FilmEntity> films = new ArrayList<>();
        when(filmRepository.findAll()).thenReturn(films);

        List<FilmEntity> foundFilms = filmService.findAll();

        Assertions.assertEquals(films, foundFilms);
        verify(filmRepository, times(1)).findAll();
    }

    @Test
    void findByName() {
        String filmName = "TestFilm";
        FilmEntity filmEntity = new FilmEntity();
        when(filmRepository.findByName(filmName)).thenReturn(Optional.of(filmEntity));

        Optional<FilmEntity> foundEntity = filmService.findByName(filmName);

        Assertions.assertEquals(Optional.of(filmEntity), foundEntity);
        verify(filmRepository, times(1)).findByName(filmName);
    }
}