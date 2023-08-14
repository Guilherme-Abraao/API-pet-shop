package com.example.petshop.base;

import com.example.petshop.agendamento.Agendamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.example.petshop.base.Role.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
@Table
public class Funcionario extends Usuario {

    @Enumerated(EnumType.STRING)
    private Cargo cargo;
    @NotNull
    private Double salario;

//    @OneToMany(mappedBy = "funcionarioId")
    @OneToMany(mappedBy = "funcionario")
    private List<Agendamento> agendamentos;


    public Funcionario(
            String nome, 
            String email, 
            String cpf, 
            String telefone, 
            String senha, 
            LocalDate dataNascimento, 
            Cargo cargo, 
            @NotNull Double salario
    ) {
        super(nome, email, cpf, telefone, senha, dataNascimento, FUNC);
        this.cargo = cargo;
        this.salario = salario;
    }

    public Funcionario(
            String nome,
            String email,
            String cpf,
            String telefone,
            String senha, LocalDate dataNascimento,
            Role role,
            Cargo cargo,
            @NotNull Double salario
    ) {
        super(nome, email, cpf, telefone, senha, dataNascimento, role);
        this.cargo = cargo;
        this.salario = salario;
    }

    //    public Funcionario(String nome, String email, String cpf, String telefone, String senha, LocalDate dataNascimento, Role role, Cargo cargo, Double salario) {
//    }
}
