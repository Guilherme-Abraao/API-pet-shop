package com.example.petshop.repository;

import com.example.petshop.base.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT cli FROM Cliente cli WHERE cli.email = ?1")
    Optional<Cliente> findClienteByEmail(String email);
}
