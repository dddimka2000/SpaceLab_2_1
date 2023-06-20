package org.example.service;

import org.example.model.RelatedFilmsHallsEntity;
import org.example.repository.RelatedFilmsHallsEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RelatedFilmsHallsEntityService {
    final
    RelatedFilmsHallsEntityRepository relatedFilmsHallsEntityRepository;

    @Autowired
    public RelatedFilmsHallsEntityService(RelatedFilmsHallsEntityRepository relatedFilmsHallsEntityRepository) {
        this.relatedFilmsHallsEntityRepository = relatedFilmsHallsEntityRepository;
    }

    public List<RelatedFilmsHallsEntity> findAll() {
        return relatedFilmsHallsEntityRepository.findAll();
    }


    public <S extends RelatedFilmsHallsEntity> S save(S entity) {
        return relatedFilmsHallsEntityRepository.save(entity);
    }

    public Optional<RelatedFilmsHallsEntity> findById(Integer integer) {
        return relatedFilmsHallsEntityRepository.findById(integer);
    }


    public void delete(RelatedFilmsHallsEntity entity) {
        relatedFilmsHallsEntityRepository.delete(entity);
    }
}
