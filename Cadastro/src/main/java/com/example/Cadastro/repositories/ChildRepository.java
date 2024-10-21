package com.example.Cadastro.repositories;

import com.example.Cadastro.models.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChildRepository extends JpaRepository<Child, UUID> {
}
