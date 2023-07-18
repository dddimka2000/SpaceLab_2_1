package org.example.service;

import lombok.extern.log4j.Log4j2;
import org.example.model.FilmEntity;
import org.example.model.ImagineEntity;
import org.example.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Log4j2
@Service
public class FilmService {
    private final
    FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public FilmEntity save(FilmEntity filmEntity) {
        log.info(filmEntity + " has been saved");

        return filmRepository.save(filmEntity);
    }

    public Optional<FilmEntity> findById(Integer integer) {
        log.info("FilmEntity with id " + integer + " has been found");
        return filmRepository.findByIdFilm(integer);
    }

    public List<FilmEntity> findAll(){
        log.info("All films have been found");

        return filmRepository.findAll();
    }
    public Optional<FilmEntity> findByName(String name){
        log.info("Film with name " + name + " has been found");
        return filmRepository.findByName(name);
    }

}
