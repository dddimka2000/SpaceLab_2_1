package org.example.service;

import org.example.model.DistributionEntity;
import org.example.repository.DistributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistributionService {
    final
    DistributionRepository distributionRepository;

    @Autowired
    public DistributionService(DistributionRepository distributionRepository) {
        this.distributionRepository = distributionRepository;
    }


    public List<DistributionEntity> findAll() {
        return distributionRepository.findAll();
    }

    public <S extends DistributionEntity> S save(S entity) {
        return distributionRepository.save(entity);
    }

    public Optional<DistributionEntity> findById(Integer integer) {
        return distributionRepository.findById(integer);
    }

    public void delete(DistributionEntity entity) {
        distributionRepository.delete(entity);
    }


}
