package com.example.petshop.usuarios;

import com.example.petshop.validation.constraints.Senha;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

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
    @NotBlank
    @Pattern(regexp = "^[A-Z]+(.)*")
    private String nome;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @CPF
    private String CPF;
    @NotBlank
    private String Telefone;
    @NotBlank
    //Validação para senha já criada, porém ainda não funciona(tentar descobrir o pq).
    //Documentos envolvidos estão na pasta validation.
    @Senha
    private String senha;
    @NotNull
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
