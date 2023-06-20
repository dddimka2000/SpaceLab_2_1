package org.example.service;

import org.example.model.FilmEntity;
import org.example.model.ImagineEntity;
import org.example.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmService {
    private final
    FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public FilmEntity save(FilmEntity filmEntity) {
        return filmRepository.save(filmEntity);
    }

    public Optional<FilmEntity> findById(Integer integer) {
        return filmRepository.findByIdFilm(integer);
    }

    public List<FilmEntity> findAll(){
        return filmRepository.findAll();
    }
    public Optional<FilmEntity> findByName(String name){
        return filmRepository.findByName(name);
    }

}
