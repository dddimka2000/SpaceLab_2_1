package org.example.repository.pages;

import org.example.model.ContactCinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactsCinemaRepository extends JpaRepository<ContactCinemaEntity, Integer> {
    @Override
    List<ContactCinemaEntity> findAll();

    @Override
    <S extends ContactCinemaEntity> S save(S entity);

    Optional<ContactCinemaEntity> findByIdCinema(Integer id);
}
