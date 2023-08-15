package com.example.petshop.repository;


import com.example.petshop.base.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    @Query("SELECT u FROM Funcionario u WHERE u.email = ?1")
    Optional<Funcionario> findFuncionarioByEmail(String email);

    @Query("SELECT u FROM Funcionario u WHERE u.cpf = ?1")
    Optional<Funcionario> findFuncionarioByCpf(String cpf);


    //Seleciona um funcionário aleatório para realizar o agendamento

    @Query("SELECT f FROM Funcionario f WHERE NOT EXISTS (SELECT 1 FROM Agendamento a WHERE a.funcionario = f AND a.dataHoraStart = :horario)")
    List<Funcionario> findFuncionariosDisponiveis(@Param("horario") LocalDateTime horario);;
}
