package org.example.service;

import lombok.extern.log4j.Log4j2;
import org.example.model.CinemaEntity;
import org.example.model.FilmEntity;
import org.example.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class CinemaService {
    private final
    CinemaRepository cinemaRepository;

    @Autowired
    public CinemaService(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public CinemaEntity save(CinemaEntity cinemaEntity) {
        log.info(cinemaEntity + " has been saved");
        return cinemaRepository.save(cinemaEntity);
    }

    public Optional<CinemaEntity> findById(Integer integer) {
        log.info("CinemaEntity with id " + integer + " has been found");
        return cinemaRepository.findByIdCinema(integer);
    }

    public List<CinemaEntity> findAll() {
        log.info("All cinemas have been found");
        return cinemaRepository.findAll();
    }

    public Optional<CinemaEntity> findLast() {
        log.info("Last cinema have been found");
        return cinemaRepository.findTopByOrderByIdCinemaDesc();
    }

    public void remove(Integer id) {
        log.info("DistributionEntity with id" + id + " has been removed");

        cinemaRepository.deleteById(id);
    }

    public Optional<CinemaEntity> findByName(String name) {
        log.info("Cinema with name " + name + " has been found");
        return cinemaRepository.findByName(name);
    }

}
