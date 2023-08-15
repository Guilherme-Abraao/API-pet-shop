package com.example.petshop.base;

import java.time.LocalDateTime;

public class EventoCalendario {

    private Long Id;
    private String Subject;
    private String StartTime;
    private String EndTime;
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
    public String getStartTime() {
        return StartTime;
    }
    public void setStartTime(String startTime) {
        StartTime = startTime;
    }
    public String getEndTime() {
        return EndTime;
    }
    public void setEndTime(String endTime) {
        EndTime = endTime;
    }
    public String getObservacoes() {
        return Observacoes;
    }
    public void setObservacoes(String observacoes) {
        Observacoes = observacoes;
    }
}
