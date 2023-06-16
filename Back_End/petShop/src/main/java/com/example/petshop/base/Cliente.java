package com.example.petshop.base;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Cliente extends Usuario {

    @OneToMany(mappedBy = "cliente")
    private List<Animal> animais;

    public Cliente(String nome, String email, String cpf, String telefone, String password, LocalDate dataNascimento, Role role) {
        super(nome, email, cpf, telefone, password, dataNascimento, role);
    }

    public Cliente(String nome, String email, String cpf, String telefone, String password, LocalDate dataNascimento, Role role, List<Animal> animais) {
        super(nome, email, cpf, telefone, password, dataNascimento, role);
        this.animais = animais;
    }

    public void adicionarAnimal(Animal animal) {
        animais.add(animal);
    }
}
