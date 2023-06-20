package org.example.service.pages;

import org.example.model.ContactCinemaEntity;
import org.example.model.MainPageEntity;
import org.example.repository.pages.ContactsCinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactsCinemaService {
    final
    ContactsCinemaRepository cinemaRepository;

    @Autowired
    public ContactsCinemaService(ContactsCinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public ContactCinemaEntity save(ContactCinemaEntity cinemaEntity) {
        return cinemaRepository.save(cinemaEntity);
    }

    public Optional<ContactCinemaEntity> findById(Integer id) {
        Optional<ContactCinemaEntity> mainPage = cinemaRepository.findByIdCinema(id);
        return mainPage;
    }

    public List<ContactCinemaEntity> findAll() {
        return cinemaRepository.findAll();
    }
}
