package com.example.petshop.base;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static com.example.petshop.base.Role.ADMINISTRADOR;

@NoArgsConstructor
@Entity
public class Administrador extends Funcionario{
    public Administrador(
            String nome,
            String email,
            String cpf,
            String telefone,
            String senha,
            LocalDate dataNascimento,
            Cargo cargo,
            @NotNull Double salario
    ) {
        super(nome, email, cpf, telefone, senha, dataNascimento, ADMINISTRADOR, cargo, salario);
    }
}
