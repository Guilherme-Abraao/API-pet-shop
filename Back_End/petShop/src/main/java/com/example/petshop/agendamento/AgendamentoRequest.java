package com.example.petshop.agendamento;

import com.example.petshop.base.Animal;
import com.example.petshop.base.Cliente;
import com.example.petshop.base.Funcionario;
import com.example.petshop.base.Servico;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AgendamentoRequest {
    private Cliente cliente;
    private Servico servico;
    private Animal animal;
    private LocalDateTime dataHora;
    private Funcionario funcionario;

    public AgendamentoRequest(Cliente cliente, Animal animal, LocalDateTime dataHora, Servico servico, Funcionario funcionario) {
        this.cliente = cliente;
        this.animal = animal;
        this.dataHora = dataHora;
        this.servico = servico;
        this.funcionario = funcionario;
    }
}

