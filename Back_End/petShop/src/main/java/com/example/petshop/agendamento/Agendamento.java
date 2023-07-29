package com.example.petshop.agendamento;

import com.example.petshop.base.Animal;
import com.example.petshop.base.Cliente;
import com.example.petshop.base.Funcionario;
import com.example.petshop.base.Servico;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Servico servico;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    // Outros campos e métodos construtores, getters e setters
}

