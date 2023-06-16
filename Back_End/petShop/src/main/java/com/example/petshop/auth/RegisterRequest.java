package com.example.petshop.auth;

import com.example.petshop.base.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String nome;
    private String email;
    private String cpf;
    private String telefone;
    private String username;
    private String password;
    private LocalDate dataNascimento;
    private Role role;
}