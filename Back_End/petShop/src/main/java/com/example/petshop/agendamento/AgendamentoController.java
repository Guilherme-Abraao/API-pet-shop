package com.example.petshop.agendamento;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/livraria/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping
    public ResponseEntity<Agendamento> agendarServico(@RequestBody AgendamentoRequest request) {
        Agendamento agendamento = agendamentoService.agendarServico(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> obterAgendamento(@PathVariable Long id) {
        Agendamento agendamento = agendamentoService.obterAgendamentoPorId(id);
        return ResponseEntity.ok(agendamento);
    }

    // Outros endpoints para atualizar e cancelar agendamentos, bem como obter todos os agendamentos do cliente, por exemplo.
}