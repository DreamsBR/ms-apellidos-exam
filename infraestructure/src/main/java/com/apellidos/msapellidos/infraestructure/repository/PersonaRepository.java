package com.apellidos.msapellidos.infraestructure.repository;

import com.apellidos.msapellidos.infraestructure.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
    List<PersonaEntity> findPersonExist();

    Optional<PersonaEntity> findByNumDocu(String numDocu);
}
