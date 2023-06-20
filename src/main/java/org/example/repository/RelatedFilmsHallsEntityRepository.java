package org.example.repository;

import org.example.model.RelatedFilmsHallsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelatedFilmsHallsEntityRepository extends JpaRepository<RelatedFilmsHallsEntity, Integer> {
    @Override
    List<RelatedFilmsHallsEntity> findAll();

    @Override
    <S extends RelatedFilmsHallsEntity> S save(S entity);

    @Override
    Optional<RelatedFilmsHallsEntity> findById(Integer integer);
}
