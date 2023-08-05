package com.example.petshop.base;

import com.example.petshop.agendamento.Agendamento;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.example.petshop.base.Role.CLIENTE;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Cliente extends Usuario {

    @OneToMany(mappedBy = "cliente")
    private List<Animal> animais;

    @OneToMany(mappedBy = "cliente")
    private List<Agendamento> agendamentos;

    public Cliente(
            String nome,
            String email,
            String cpf,
            String telefone,
            String senha,
            LocalDate dataNascimento
    ) {
        super(nome, email, cpf, telefone, senha, dataNascimento, CLIENTE);
    }

    public Cliente(
            String nome,
            String email,
            String cpf,
            String telefone,
            String senha,
            LocalDate dataNascimento,
            List<Animal> animais
    ) {
        super(nome, email, cpf, telefone, senha, dataNascimento, CLIENTE);
        this.animais = animais;
    }

    public void adicionarAnimal(Animal animal) {
        animais.add(animal);
    }
}
