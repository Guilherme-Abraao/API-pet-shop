package com.example.petshop.agendamento;

import com.example.petshop.base.Animal;
import com.example.petshop.base.Cliente;
import com.example.petshop.base.Funcionario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
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

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
//    private Long clienteId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;
//    private Long funcionarioId;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "servicos")
    private List<Servico> servicos;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;
//    private Long animalId;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(nullable = false)
    private LocalDateTime dataHoraStart;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(nullable = false)
    @Transient
    private LocalDateTime dataHoraEnd;

    @Column
    private String observacoes;

    public LocalDateTime getDataHoraEnd() {
        return dataHoraStart.plusMinutes(30);
    }
}

