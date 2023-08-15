package com.example.petshop.base;

import com.example.petshop.agendamento.Agendamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.example.petshop.base.Role.USER;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Cliente extends Usuario {

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "cliente"
    )
    @JsonManagedReference
    private List<Animal> animais;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "cliente"
    )
    @JsonIgnore
    @JsonManagedReference
    private List<Agendamento> agendamentos;

    public Cliente(
            String nome,
            String email,
            String cpf,
            String telefone,
            String senha,
            LocalDate dataNascimento
    ) {
        super(nome, email, cpf, telefone, senha, dataNascimento, USER);
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
        super(nome, email, cpf, telefone, senha, dataNascimento, USER);
        this.animais = animais;
    }
}
