package com.example.petshop.base;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EventoCalendario {

    private Long id;
    private String subject;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String observacoes;

    public void setId(Long id) {
        this.id = id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

   
}
