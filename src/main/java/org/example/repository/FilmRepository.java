package org.example.repository;

import org.example.model.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity,Integer> {
    @Override
    <S extends FilmEntity> S save(S entity);

    Optional<FilmEntity> findByIdFilm(Integer integer);

    @Override
    List<FilmEntity> findAll();

    Optional<FilmEntity> findByName(String name);

}
