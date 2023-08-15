package com.example.petshop.repository;


import com.example.petshop.base.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    @Query("SELECT u FROM Funcionario u WHERE u.email = ?1")
    Optional<Funcionario> findFuncionarioByEmail(String email);

    @Query("SELECT u FROM Funcionario u WHERE u.cpf = ?1")
    Optional<Funcionario> findFuncionarioByCpf(String cpf);
}
