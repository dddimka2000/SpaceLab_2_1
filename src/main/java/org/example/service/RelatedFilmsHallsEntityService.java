package org.example.service;

import lombok.extern.log4j.Log4j2;
import org.example.model.RelatedFilmsHallsEntity;
import org.example.repository.RelatedFilmsHallsEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Log4j2
@Service
public class RelatedFilmsHallsEntityService {
    final
    RelatedFilmsHallsEntityRepository relatedFilmsHallsEntityRepository;

    @Autowired
    public RelatedFilmsHallsEntityService(RelatedFilmsHallsEntityRepository relatedFilmsHallsEntityRepository) {
        this.relatedFilmsHallsEntityRepository = relatedFilmsHallsEntityRepository;
    }

    public List<RelatedFilmsHallsEntity> findAll() {
        log.info("All rows \"relatedFilmsHallsEntityRepository\" have been found");
        return relatedFilmsHallsEntityRepository.findAll();
    }
    public <S extends RelatedFilmsHallsEntity> S save(S entity) {
        log.info(entity + " has been saved");
        return relatedFilmsHallsEntityRepository.save(entity);
    }

    public Optional<RelatedFilmsHallsEntity> findById(Integer integer) {
        log.info("RelatedFilmsHallsEntity with id " + integer + " has been found");
        return relatedFilmsHallsEntityRepository.findById(integer);
    }

    public void delete(RelatedFilmsHallsEntity entity) {
        log.info("RelatedFilmsHallsEntity with id" + entity.getId() + " has been removed");
        relatedFilmsHallsEntityRepository.delete(entity);
    }
}
