package com.example.petshop.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventoCalendario {

    private Long Id;
    private String Subject;
    private String StartTime;
    private String EndTime;
    private String Observacoes;
}
