package org.example.repository;

import org.example.model.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CinemaRepository extends JpaRepository<CinemaEntity,Integer> {
     Optional<CinemaEntity> findByIdCinema(Integer id);

     Optional<CinemaEntity> findTopByOrderByIdCinemaDesc();

     @Override
     void deleteById(Integer integer);
     Optional<CinemaEntity> findByName(String name);

}
