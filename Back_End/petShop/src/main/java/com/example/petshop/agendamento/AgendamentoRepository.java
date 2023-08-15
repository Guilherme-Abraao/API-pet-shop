package com.example.petshop.agendamento;

import com.example.petshop.base.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

//    @Query("SELECT COUNT(a) FROM Agendamento a WHERE a.dataHora = :horario")
//    int countByDataHora(LocalDateTime horario);
//
//    @Query("SELECT COUNT(a) FROM Agendamento a WHERE a.funcionarioId = :funcionarioId AND a.dataHoraStart = :horario")
    @Query("SELECT COUNT(a) FROM Agendamento a WHERE a.funcionario.id = :funcionarioId AND a.dataHoraStart = :horario")
    int countByFuncionarioAndDataHora(Long funcionarioId, LocalDateTime horario);

    @Query("SELECT a FROM Agendamento a WHERE a.cliente.id = :clienteId")
    List<Agendamento> findClienteById(Long clienteId);

    List<Agendamento> findByDataHoraStartBetween(LocalDateTime startDate, LocalDateTime endDate);
}
