package com.example.Cadastro.repositories;

import com.example.Cadastro.models.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StateRepository extends JpaRepository<State, UUID> {
    Optional<State> findByName(String name);
    Optional<State> findByAbbreviation(String abbreviation);
}
