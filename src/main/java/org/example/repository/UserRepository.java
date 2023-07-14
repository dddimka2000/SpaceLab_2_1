package org.example.repository;

import org.example.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findFirstByLog(String log);
    @Transactional
    @Override
    <S extends UserEntity> S save(S entity);

    Page<UserEntity> findAll(Pageable pageable);

    @Override
    void deleteById(Integer integer);
}
