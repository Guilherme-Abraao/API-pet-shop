package com.example.petshop.base;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Entity
public class Cliente extends Usuario {

    @OneToMany(mappedBy = "cliente")
    private List<Animal> animais;

    public Cliente(String nome, String email, String cpf, String telefone, String senha, LocalDate dataNascimento) {
        super(nome, email, cpf, telefone, senha, dataNascimento);
    }
}
