package com.example.petshop.repository;

import com.example.petshop.base.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    @Query("SELECT adm FROM Administrador adm WHERE adm.email = ?1")
    Optional<Administrador> findAdministradorByEmail(String email);
}
