package org.example.service.pages;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.example.model.ContactCinemaEntity;
import org.example.model.MainPageEntity;
import org.example.repository.pages.ContactsCinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ContactsCinemaService {
    final
    ContactsCinemaRepository cinemaRepository;

    @Autowired
    public ContactsCinemaService(ContactsCinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public ContactCinemaEntity save(ContactCinemaEntity cinemaEntity) {
        log.info(cinemaEntity + " has been saved");
        return cinemaRepository.save(cinemaEntity);
    }

    public Optional<ContactCinemaEntity> findById(Integer id) {
        Optional<ContactCinemaEntity> mainPage = cinemaRepository.findByIdCinema(id);
        log.info("ContactCinemaEntity with id " + id + " has been found");

        return mainPage;
    }

    public List<ContactCinemaEntity> findAll() {
        log.info("ContactCinemaEntities have been found");
        return cinemaRepository.findAll();
    }
}
