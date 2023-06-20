package org.example.repository;

import org.example.model.SessionEntity;
import org.example.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Integer> {
    @Override
    List<SessionEntity> findAll();

    @Override
    <S extends SessionEntity> S save(S entity);

    @Override
    Optional<SessionEntity> findById(Integer integer);

    List<SessionEntity> findByUser(UserEntity user);
}
