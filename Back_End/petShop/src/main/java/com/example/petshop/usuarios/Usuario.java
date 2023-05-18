package com.example.petshop.usuarios;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Data
@Entity
@Table
public class Usuario {

    @Id
    @SequenceGenerator(
            name = "usuario_sequence",
            sequenceName = "usuario_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "usuario_sequence"
    )
    private Long id;
    private String nome;
    private String email;
    private String CPF;
    private String Telefone;
    private String senha;
//    private String endereco;
    private LocalDate dataNascimento;
//    @Transient
//    private int idade;

    public Usuario() {
    }

    public Usuario(String nome, String email, String CPF, String telefone, String senha, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.CPF = CPF;
        Telefone = telefone;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
    }

    //    Caso queira mostrar a idade
//    public int getIdade() {
//        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
//    }
}
