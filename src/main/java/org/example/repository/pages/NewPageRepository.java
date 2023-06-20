package org.example.repository.pages;

import org.example.model.PageEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewPageRepository extends JpaRepository<PageEntity,Integer> {

    Optional<PageEntity> findByIdPage(Integer id);

    Optional<PageEntity> findByName(String name);

    List<PageEntity> findAll();

    @Override
    void delete(PageEntity entity);
}
