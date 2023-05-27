package com.example.petshop.base;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Entity
public class Cliente extends Usuario {

    public Cliente(String nome, String email, String cpf, String telefone, String senha, LocalDate dataNascimento) {
        super(nome, email, cpf, telefone, senha, dataNascimento);
    }
}
