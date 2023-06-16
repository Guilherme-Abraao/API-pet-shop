package com.example.petshop.base;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Entity
public class Administrador extends Funcionario{
    public Administrador(String nome, String email, String CPF, String telefone, String senha, LocalDate dataNascimento, Role role, String cargo, Double salario) {
        super(nome, email, CPF, telefone, senha, dataNascimento, role, cargo, salario);
    }
}
