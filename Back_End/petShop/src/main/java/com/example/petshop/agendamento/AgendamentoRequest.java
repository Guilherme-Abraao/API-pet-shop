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
    private Funcionario funcionario;
    private List<Servico> servicos;
    private Animal animal;
    private LocalDateTime StartTime;
    private String observacoes;

    public AgendamentoRequest(
            Cliente cliente,
            Funcionario funcionario,
            List<Servico> servicos,
            Animal animal,
            LocalDateTime StartTime) {
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.servicos = servicos;
        this.animal = animal;
        this.StartTime = StartTime;
    }
}

