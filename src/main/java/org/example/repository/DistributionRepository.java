package org.example.repository;

import org.example.model.DistributionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DistributionRepository extends JpaRepository<DistributionEntity, Integer> {
    @Override
    List<DistributionEntity> findAll();

    @Override
    <S extends DistributionEntity> S save(S entity);

    @Override
    Optional<DistributionEntity> findById(Integer integer);

    @Override
    void delete(DistributionEntity entity);

}
