package com.example.petshop.funcionario;

import com.example.petshop.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table
public class Funcionario extends Usuario {

    private String cargo;
    private Double salario;

    public Funcionario(){
    }

    public Funcionario(String nome, String email, String CPF, String telefone, String senha, LocalDate dataNascimento, String cargo, Double salario) {
        super(nome, email, CPF, telefone, senha, dataNascimento);
        this.cargo = cargo;
        this.salario = salario;
    }
}
