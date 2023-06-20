package org.example.repository;

import org.example.model.ImagineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ImagineRepository extends JpaRepository<ImagineEntity, Integer> {
    @Override
    <S extends ImagineEntity> S save(S entity);

    @Override
    Optional<ImagineEntity> findById(Integer integer);

    ArrayList<ImagineEntity> findByName(String name);

    @Override
    void delete(ImagineEntity entity);

    Optional <ImagineEntity> findByPath(String path);

}
