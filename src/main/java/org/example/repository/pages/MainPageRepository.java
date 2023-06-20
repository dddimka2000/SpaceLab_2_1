package org.example.repository.pages;

import org.example.model.MainPageEntity;
import org.example.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MainPageRepository extends JpaRepository<MainPageEntity, Integer> {
    @Override
    <S extends MainPageEntity> S save(S entity);

    @Override
    Optional<MainPageEntity> findById(Integer integer);

    Optional<MainPageEntity> findFirstByIdMainPage(Integer id);

    Optional<MainPageEntity> findFirstByNamePage(String name);

}
