package com.example.petshop.agendamento;

import com.example.petshop.base.Animal;
import com.example.petshop.base.Cliente;
import com.example.petshop.base.Funcionario;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AgendamentoRequest {
    private Cliente cliente;
    private Funcionario funcionario;
    private Servico servico;
    private Animal animal;
    private LocalDateTime dataHora;
    private String observacoes;

    public AgendamentoRequest(
            Cliente cliente,
            Funcionario funcionario,
            Animal animal,
            Servico servico,
            LocalDateTime dataHora
    ) {
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.animal = animal;
        this.servico = servico;
        this.dataHora = dataHora;
    }

    public AgendamentoRequest(
            Cliente cliente,
            Funcionario funcionario,
            Servico servico,
            Animal animal,
            LocalDateTime dataHora,
            String observacoes
    ) {
        this.cliente = cliente;
        this.funcionario = funcionario;
        this.servico = servico;
        this.animal = animal;
        this.dataHora = dataHora;
        this.observacoes = observacoes;
    }
}

