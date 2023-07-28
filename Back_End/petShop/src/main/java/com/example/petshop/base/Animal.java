package com.example.petshop.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AllArgsConstructor
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
    @Transient
    private int idade;

    private LocalDate dataNascimento;

    private String raca;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "cliente_id"
    )
    private Cliente cliente;

    public Animal(String nome, LocalDate dataNascimento, String raca, Cliente cliente) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.raca = raca;
        this.cliente = cliente;
    }

    public int getIdade() {
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }
}
