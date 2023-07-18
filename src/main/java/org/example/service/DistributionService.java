package org.example.service;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.example.model.DistributionEntity;
import org.example.repository.DistributionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Log4j2
@Service
public class DistributionService {
    final
    DistributionRepository distributionRepository;

    @Autowired
    public DistributionService(DistributionRepository distributionRepository) {
        this.distributionRepository = distributionRepository;
    }


    public List<DistributionEntity> findAll() {
        log.info("All DistributionEntity have been found");
        return distributionRepository.findAll();
    }

    public <S extends DistributionEntity> S save(S entity) {

        log.info(entity + " has been saved");
        return distributionRepository.save(entity);
    }

    public Optional<DistributionEntity> findById(Integer integer) {
        log.info("DistributionEntity with id " + integer + " has been found");
        return distributionRepository.findById(integer);
    }

    public void delete(DistributionEntity entity) {
        log.info("DistributionEntity with id" + entity.getId() + " has been removed");
        distributionRepository.delete(entity);
    }


}
