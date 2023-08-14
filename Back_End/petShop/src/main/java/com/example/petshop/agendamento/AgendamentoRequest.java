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
    private Cliente cliente;
//    private Long clienteId;
    private Funcionario funcionario;
//    private Long funcionarioId;
    private List<Servico> servicos;
    private Animal animal;
//    private Long animalId;
    private LocalDateTime dataHoraStart;
    private String observacoes;

    public AgendamentoRequest(
            Cliente cliente,
//            Long clienteId,
            Funcionario funcionario,
//            Long funcionarioId,
            List<Servico> servicos,
            Animal animal,
//            Long animalId,
            LocalDateTime dataHoraStart) {
        this.cliente = cliente;
//        this.clienteId = clienteId;
        this.funcionario = funcionario;
//        this.funcionarioId = funcionarioId;
        this.servicos = servicos;
        this.animal = animal;
//        this.animalId = animalId;
        this.dataHoraStart = dataHoraStart;
    }
}

