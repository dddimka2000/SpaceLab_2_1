package org.example.service;

import org.example.model.CinemaEntity;
import org.example.model.HallEntity;
import org.example.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HallService {
    private final
    HallRepository hallRepository;
    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public Optional<HallEntity> findById(Integer integer){
         return hallRepository.findById(integer);
    }
    public HallEntity save(HallEntity hallEntity){
        return hallRepository.save(hallEntity);
    }


    public List<HallEntity> findByEmptyCinemaId(){
        return hallRepository.findByCinemaEntityNull();
    }

    public List<HallEntity> findByCinemaId(Integer id){
        return hallRepository.findByCinemaEntityIdCinema(id);
    }
    public void remove(Integer id){
        hallRepository.deleteById(id);
    }
}