package com.example.petshop.base;

import java.time.LocalDateTime;

public class EventoCalendario {

    private Long Id;
    private String Subject;
    private LocalDateTime StartTime;
    private LocalDateTime EndTime;
    private String Observacoes;

    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }
    public String getSubject() {
        return Subject;
    }
    public void setSubject(String subject) {
        Subject = subject;
    }
    public LocalDateTime getStartTime() {
        return StartTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        StartTime = startTime;
    }
    public LocalDateTime getEndTime() {
        return EndTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        EndTime = endTime;
    }
    public String getObservacoes() {
        return Observacoes;
    }
    public void setObservacoes(String observacoes) {
        Observacoes = observacoes;
    }
}
