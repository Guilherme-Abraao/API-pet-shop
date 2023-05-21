package com.example.petshop.funcionario;

import com.example.petshop.usuarios.Usuario;
import com.example.petshop.validation.constraints.Senha;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table
public class Funcionario extends Usuario {

    @NotBlank
    private String cargo;
    @NotNull
    private Double salario;

    public Funcionario(){
    }

    public Funcionario(String nome, String email, String CPF, String telefone, String senha, LocalDate dataNascimento, String cargo, Double salario) {
        super(nome, email, CPF, telefone, senha, dataNascimento);
        this.cargo = cargo;
        this.salario = salario;
    }
}
