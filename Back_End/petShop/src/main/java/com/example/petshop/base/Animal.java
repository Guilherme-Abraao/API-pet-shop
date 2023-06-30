package com.example.petshop.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "Animal")
public class Animal {

    @Id
    @SequenceGenerator(
            name = "animal_sequence",
            sequenceName = "animal_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "animal_sequence"
    )
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "name.not.blank")
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Column(name = "idade")
    private int idade;
    /*
    * Declarar data de nascimento, espécie e raça
    * */

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "cliente_id"
    )
    private Cliente cliente;

    public Animal(String nome, int idade, Cliente cliente) {
        this.nome = nome;
        this.idade = idade;
        this.cliente = cliente;
    }
}
