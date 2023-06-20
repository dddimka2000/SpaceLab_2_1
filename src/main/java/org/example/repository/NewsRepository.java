package org.example.repository;

import org.example.model.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;
@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Integer> {
    @Override
    List<NewsEntity> findAll();

    @Override
    <S extends NewsEntity> S save(S entity);

    @Override
    Optional<NewsEntity> findById(Integer integer);

    @Override
    void delete(NewsEntity entity);
}
