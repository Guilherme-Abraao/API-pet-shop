package com.example.petshop.base;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventoCalendario {

    private Long Id;
    private String Subject;
    private LocalDateTime StartTime;
    private LocalDateTime EndTime;
    private String Observacoes;
}
