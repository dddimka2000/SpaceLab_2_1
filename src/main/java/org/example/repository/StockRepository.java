package org.example.repository;

import org.example.model.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface StockRepository extends JpaRepository<StockEntity,Integer> {
    @Override
    List<StockEntity> findAll();

    @Override
    <S extends StockEntity> S save(S entity);

    @Override
    Optional<StockEntity> findById(Integer integer);

    @Override
    void delete(StockEntity entity);

}
