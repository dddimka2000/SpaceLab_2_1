package org.example.service;

import org.example.model.CinemaEntity;
import org.example.model.FilmEntity;
import org.example.repository.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {
    private final
    CinemaRepository cinemaRepository;

    @Autowired
    public CinemaService(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }
    public CinemaEntity save(CinemaEntity cinemaEntity){
        return cinemaRepository.save(cinemaEntity);
    }
    public Optional<CinemaEntity> findById(Integer integer) {
        return cinemaRepository.findByIdCinema(integer);
    }

    public List<CinemaEntity> findAll(){
        return cinemaRepository.findAll();
    }
    public Optional<CinemaEntity> findLast(){
        return cinemaRepository.findTopByOrderByIdCinemaDesc();
    }
    public void remove(Integer id){
        cinemaRepository.deleteById(id);
    }
    public Optional<CinemaEntity> findByName(String name) {
        return cinemaRepository.findByName(name);
    }

}
