package com.example.petshop.agendamento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoRequest {
    private LocalDateTime dataHoraStart;
    private Long clienteId;
    private Long animalId;
    private List<Servico> servicos;
    private String observacoes;
    private Long funcionarioId;

    public AgendamentoRequest(
            LocalDateTime dataHoraStart,
            Long clienteId,
            Long animalId,
            List<Servico> servicos,
            Long funcionarioId
    ) {
        this.dataHoraStart = dataHoraStart;
        this.clienteId = clienteId;
        this.animalId = animalId;
        this.servicos = servicos;
        this.funcionarioId = funcionarioId;
    }
}

