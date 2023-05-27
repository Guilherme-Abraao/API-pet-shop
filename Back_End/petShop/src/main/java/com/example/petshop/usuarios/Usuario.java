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

    @NotBlank(message = "name.not.blank")
    @Pattern(regexp = "^[A-Z]+(.)*")
    private String nome;

    @NotBlank(message = "email.not.blank")
    @Email(message = "email.not.valid")
    private String email;

    @NotBlank(message = "cpf.not.blank")
    @CPF(message = "cpf.not.valid")
    private String cpf;

    @NotBlank(message = "telefone.not.blank")
    private String Telefone;

    @NotBlank(message = "senha.not.blank")
    //Validação para senha já criada, porém ainda não funciona(tentar descobrir o pq).
    //Documentos envolvidos estão na pasta validation.
    @Senha(message = "senha.not.valid")
    private String senha;

//    @NotBlank(message = "senha.not.blank")
//    //Validação para senha já criada, porém ainda não funciona(tentar descobrir o pq).
//    //Documentos envolvidos estão na pasta validation.
////    @Senha(message = "senha.not.valid")
//    private String confirmacaoSenha;

    @NotNull(message = "dataNascimento.not.null")
    private LocalDate dataNascimento;

<<<<<<< HEAD:Back_End/petShop/src/main/java/com/example/petshop/usuarios/Usuario.java
    public Usuario() {
    }

    public Usuario(String nome, String email, String CPF, String telefone, String senha, LocalDate dataNascimento) {
=======
    public Usuario(String nome, String email, String cpf, String telefone, String senha, LocalDate dataNascimento) {
>>>>>>> parent of 67731d8... Merge pull request #48 from gilmarUFG/guilherme:Back_End/petShop/src/main/java/com/example/petshop/base/Usuario.java
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        Telefone = telefone;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
    }

//    public Usuario(String nome, String email, String cpf, String telefone, String senha, String confirmacaoSenha, @NotNull(message = "dataNascimento.not.null") LocalDate dataNascimento) {
//        this.nome = nome;
//        this.email = email;
//        this.cpf = cpf;
//        Telefone = telefone;
//        this.senha = senha;
//        this.confirmacaoSenha = confirmacaoSenha;
//        this.dataNascimento = dataNascimento;
//    }
}
