package com.example.petshop.base;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
@Data
public class Cliente extends Usuario {

    @OneToMany(mappedBy = "cliente")
    private List<Animal> animais;

    public Cliente(String nome, String email, String cpf, String telefone, String senha, LocalDate dataNascimento) {
        super(nome, email, cpf, telefone, senha, dataNascimento);
    }

    public Cliente(String nome, String email, String cpf, String telefone, String senha, LocalDate dataNascimento, List<Animal> animais) {
        super(nome, email, cpf, telefone, senha, dataNascimento);
        this.animais = animais;
    }

    public void adicionarAnimal(Animal animal) {
        animais.add(animal);
    }
}
