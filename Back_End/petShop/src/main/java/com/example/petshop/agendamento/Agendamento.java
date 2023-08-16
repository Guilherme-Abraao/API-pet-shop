package com.example.petshop.agendamento;

import com.example.petshop.base.Animal;
import com.example.petshop.base.Cliente;
import com.example.petshop.base.Funcionario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Agendamento {

    @Id
    @SequenceGenerator(
            name = "agendamento_sequence",
            sequenceName = "agendamento_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "agendamento_sequence"
    )
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", shape = JsonFormat.Shape.STRING)
    @Column(nullable = false)
    private LocalDateTime dataHoraStart;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", shape = JsonFormat.Shape.STRING)
    @Column(nullable = false)
    @Transient
    private LocalDateTime dataHoraEnd;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "servicos")
    private List<Servico> servicos;

    @Column
    private String observacoes;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    public LocalDateTime getDataHoraEnd(Agendamento agendamento) {
        int tempoExtra = (agendamento.servicos.size() * 20);
        return dataHoraStart.plusMinutes(30 + tempoExtra);
    }

    @JsonProperty("clienteId")
    public Long getClienteId() {
        return cliente.getId();
    }

    @JsonProperty("animalId")
    public Long getAnimalId() {
        return animal.getId();
    }

    @JsonProperty("funcionarioId")
    public Long getFuncionarioId() {
        return funcionario.getId();
    }

    @JsonProperty("funcionarioNome")
    public String getFuncionarioNome() {
        return funcionario.getNome();
    }
}

