package com.example.petshop.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    @Query("SELECT u FROM Funcionario u WHERE u.email = ?1")
    Optional<Funcionario> findUsuarioByEmail(String email);
}
