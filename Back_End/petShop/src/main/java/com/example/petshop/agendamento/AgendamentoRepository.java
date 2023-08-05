package com.example.petshop.agendamento;

import com.example.petshop.base.Animal;
import com.example.petshop.base.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

//    @Query("SELECT COUNT(a) FROM Agendamento a WHERE a.dataHora = :horario")
//    int countByDataHora(LocalDateTime horario);
//
//    @Query("SELECT COUNT(a) FROM Agendamento a WHERE a.funcionario = :funcionario")
    int countByFuncionarioAndDataHora(Funcionario funcionario, LocalDateTime horario);
}
