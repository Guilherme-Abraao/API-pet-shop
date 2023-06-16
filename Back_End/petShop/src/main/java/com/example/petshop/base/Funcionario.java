package com.example.petshop.base;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
@Table
public class Funcionario extends Usuario {

    @NotBlank
    private String cargo;
    @NotNull
    private Double salario;

    public Funcionario(String nome, String email, String CPF, String telefone, String senha, LocalDate dataNascimento, Role role, String cargo, Double salario) {
        super(nome, email, CPF, telefone, senha, dataNascimento, role);
        this.cargo = cargo;
        this.salario = salario;
    }
}
