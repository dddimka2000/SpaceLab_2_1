package org.example.service.pages;

import org.example.model.ContactCinemaEntity;
import org.example.repository.pages.ContactsCinemaRepository;
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
class ContactsCinemaServiceTest {
    @Mock
    private ContactsCinemaRepository cinemaRepository;

    @InjectMocks
    private ContactsCinemaService cinemaService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void save() {
        ContactCinemaEntity cinemaEntity = new ContactCinemaEntity();
        when(cinemaRepository.save(cinemaEntity)).thenReturn(cinemaEntity);

        ContactCinemaEntity savedEntity = cinemaService.save(cinemaEntity);

        Assertions.assertEquals(cinemaEntity, savedEntity);
        verify(cinemaRepository, times(1)).save(cinemaEntity);
    }

    @Test
    void findById() {
        Integer cinemaId = 1;
        ContactCinemaEntity cinemaEntity = new ContactCinemaEntity();
        when(cinemaRepository.findByIdCinema(cinemaId)).thenReturn(Optional.of(cinemaEntity));

        Optional<ContactCinemaEntity> foundEntity = cinemaService.findById(cinemaId);

        Assertions.assertEquals(Optional.of(cinemaEntity), foundEntity);
        verify(cinemaRepository, times(1)).findByIdCinema(cinemaId);
    }
    @Test
    void findAll() {
        List<ContactCinemaEntity> cinemas = new ArrayList<>();
        when(cinemaRepository.findAll()).thenReturn(cinemas);
        List<ContactCinemaEntity> foundCinemas = cinemaService.findAll();

        Assertions.assertEquals(cinemas, foundCinemas);
        verify(cinemaRepository, times(1)).findAll();
    }
}