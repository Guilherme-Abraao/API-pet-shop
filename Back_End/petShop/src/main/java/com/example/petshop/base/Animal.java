package com.example.petshop.base;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
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

    @ManyToOne
    @JoinColumn(
            name = "cliente_id",
            nullable = false
    )
    private Cliente cliente;

    public Animal(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }
}
