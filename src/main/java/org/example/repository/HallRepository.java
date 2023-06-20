package org.example.repository;

import org.example.model.HallEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface HallRepository extends JpaRepository<HallEntity,Integer> {
    List<HallEntity> findByCinemaEntityNull();
    List<HallEntity> findByCinemaEntityIdCinema(Integer id);

    @Override
    void deleteById(Integer integer);
}
