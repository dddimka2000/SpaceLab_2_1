package org.example.service;

import lombok.extern.log4j.Log4j2;
import org.example.model.ImagineEntity;
import org.example.repository.ImagineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
@Log4j2
@Service
public class ImagineService {
    final
    ImagineRepository imagineRepository;

    @Autowired
    public ImagineService(ImagineRepository imagineRepository) {
        this.imagineRepository = imagineRepository;
    }

    public ImagineEntity save(ImagineEntity imagineEntity) {
        log.info(imagineEntity + " has been saved");
        return imagineRepository.save(imagineEntity);
    }

    public Optional<ImagineEntity> findById(Integer integer) {
        log.info("ImagineEntity with id " + integer + " has been found");
        return imagineRepository.findById(integer);
    }
    public ArrayList<ImagineEntity> findByName(String name) {
        log.info("ImagineEntity with name " + name + " has been found");
        return imagineRepository.findByName(name);
    }

    public void delete(ImagineEntity entity) {
        log.info("ImagineEntity with id" + entity.getId() + " has been removed");
        imagineRepository.delete(entity);
    }
}
