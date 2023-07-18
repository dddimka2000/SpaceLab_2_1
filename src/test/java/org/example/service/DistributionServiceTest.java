package org.example.service;

import org.example.model.DistributionEntity;
import org.example.repository.DistributionRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
class DistributionServiceTest {
    @Mock
    private DistributionRepository distributionRepository;

    @InjectMocks
    private DistributionService distributionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        List<DistributionEntity> distributions = new ArrayList<>();
        when(distributionRepository.findAll()).thenReturn(distributions);


        List<DistributionEntity> foundDistributions = distributionService.findAll();

        Assertions.assertEquals(distributions, foundDistributions);
        verify(distributionRepository, times(1)).findAll();
    }

    @Test
    void save() {
        DistributionEntity distributionEntity = new DistributionEntity();
        when(distributionRepository.save(distributionEntity)).thenReturn(distributionEntity);

        DistributionEntity savedEntity = distributionService.save(distributionEntity);

        Assertions.assertEquals(distributionEntity, savedEntity);
        verify(distributionRepository, times(1)).save(distributionEntity);
    }

    @Test
    void findById() {   Integer distributionId = 1;
        DistributionEntity distributionEntity = new DistributionEntity();
        when(distributionRepository.findById(distributionId)).thenReturn(Optional.of(distributionEntity));

        Optional<DistributionEntity> foundEntity = distributionService.findById(distributionId);

        Assertions.assertEquals(Optional.of(distributionEntity), foundEntity);
        verify(distributionRepository, times(1)).findById(distributionId);
    }

    @Test
    void delete() {
        DistributionEntity distributionEntity = new DistributionEntity();

        distributionService.delete(distributionEntity);

        verify(distributionRepository, times(1)).delete(distributionEntity);
    }
}