package com.example.petshop.agendamento;

import com.example.petshop.base.Animal;
import com.example.petshop.base.Cliente;
import com.example.petshop.base.Funcionario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoRequest {
    private Long clienteId;
    private Long funcionarioId;
    private List<Servico> servicos;
    private Long animalId;
    private LocalDateTime dataHoraStart;
    private String observacoes;

    public AgendamentoRequest(
            Long clienteId,
            Long funcionarioId,
            List<Servico> servicos,
            Long animalId,
            LocalDateTime dataHoraStart) {
        this.clienteId = clienteId;
        this.funcionarioId = funcionarioId;
        this.servicos = servicos;
        this.animalId = animalId;
        this.dataHoraStart = dataHoraStart;
    }
}

