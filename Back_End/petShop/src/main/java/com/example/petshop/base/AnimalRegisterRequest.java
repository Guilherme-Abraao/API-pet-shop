package com.example.petshop.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalRegisterRequest {
    String nome;
    LocalDate dataNascimento;
    String raca;
    String especie;
    @JsonIgnore
    private Cliente cliente;
}
