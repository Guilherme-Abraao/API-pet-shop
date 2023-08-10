package com.example.petshop.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private String senha;
    private LocalDate dataNascimento;
    private Cargo cargo;
    private Double salario;
}
