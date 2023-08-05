package com.example.petshop.agendamento;

import com.example.petshop.base.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/petshop/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> getAllAgendamentos() {
        List<Agendamento> agendamentos = agendamentoService.getAgendamentos();
        return new ResponseEntity<>(agendamentos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> obterAgendamento(@PathVariable Long id) {
        Agendamento agendamento = agendamentoService.obterAgendamentoPorId(id);
        return ResponseEntity.ok(agendamento);
    }

    @PostMapping(path = "/agendar")
    public ResponseEntity<List<Agendamento>> agendarServicos(@RequestBody List<AgendamentoRequest> requests) {
        List<Agendamento> agendamento = agendamentoService.agendarServicos(requests);
        return new ResponseEntity<>(agendamento, HttpStatus.CREATED);
    }

    // Outros endpoints para atualizar e cancelar agendamentos, bem como obter todos os agendamentos do cliente, por exemplo.
}