package com.example.petshop.base;

import com.example.petshop.agendamento.Agendamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
@Table
public class Funcionario extends Usuario {

    @Enumerated(EnumType.STRING)
    private Cargo cargo;
    @NotNull
    private Double salario;

    @OneToMany(mappedBy = "funcionario")
    private List<Agendamento> agendamentos;


    public Funcionario(String nome, String email, String CPF, String telefone, String senha, LocalDate dataNascimento, Role role, Cargo cargo, Double salario) {
        super(nome, email, CPF, telefone, senha, dataNascimento, role);
        this.cargo = cargo;
        this.salario = salario;
    }
}
