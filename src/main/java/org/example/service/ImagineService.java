package org.example.service;

import org.example.model.ImagineEntity;
import org.example.repository.ImagineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ImagineService {
    final
    ImagineRepository imagineRepository;

    @Autowired
    public ImagineService(ImagineRepository imagineRepository) {
        this.imagineRepository = imagineRepository;
    }

    public ImagineEntity save(ImagineEntity imagineEntity) {
        return imagineRepository.save(imagineEntity);
    }

    public Optional<ImagineEntity> findById(Integer integer) {
        return imagineRepository.findById(integer);
    }
    public ArrayList<ImagineEntity> findByName(String name) {
        return imagineRepository.findByName(name);
    }

    public void delete(ImagineEntity entity) {
        imagineRepository.delete(entity);
    }
    public Optional <ImagineEntity> findByPath(String path) {
        return imagineRepository.findByPath(path);
    }
}
