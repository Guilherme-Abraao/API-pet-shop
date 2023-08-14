package com.example.petshop.base;

import com.example.petshop.agendamento.Agendamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AllArgsConstructor
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
    private String especie;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "cliente_id"
    )
    private Cliente cliente;

//    @OneToMany(mappedBy = "animalId")
    @OneToMany(mappedBy = "animal")
    private List<Agendamento> agendamentos;

    public Animal(String nome, LocalDate dataNascimento, String especie, String raca, Cliente cliente) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.especie = especie;
        this.raca = raca;
        this.cliente = cliente;
    }

    public int getIdade() {
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }
}
