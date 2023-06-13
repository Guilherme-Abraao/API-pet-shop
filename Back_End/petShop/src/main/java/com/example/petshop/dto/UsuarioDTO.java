package com.example.petshop.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UsuarioDTO {
    Long id;
    String nome;
    String email;
    String cpf;
    String telefone;
    LocalDate dataNascimento;
    List<String> roles;
}
