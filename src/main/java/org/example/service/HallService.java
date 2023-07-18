package org.example.service;

import lombok.extern.log4j.Log4j2;
import org.example.model.CinemaEntity;
import org.example.model.HallEntity;
import org.example.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class HallService {
    private final
    HallRepository hallRepository;
    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public Optional<HallEntity> findById(Integer integer){
        log.info("News with id " + integer + " has been found");
        return hallRepository.findById(integer);
    }
    public HallEntity save(HallEntity hallEntity){
        log.info(hallEntity + " has been saved");
        return hallRepository.save(hallEntity);
    }


    public List<HallEntity> findByEmptyCinemaId(){
        log.info("All halls with empty cinema_id have been found ");
        return hallRepository.findByCinemaEntityNull();
    }

    public List<HallEntity> findByCinemaId(Integer id){
        log.info("Halls with cinema_id " + id + " have been found");
        return hallRepository.findByCinemaEntityIdCinema(id);
    }
    public void remove(Integer id){
        log.info("Hall with id "+id + " has been removed");
        hallRepository.deleteById(id);
    }
}