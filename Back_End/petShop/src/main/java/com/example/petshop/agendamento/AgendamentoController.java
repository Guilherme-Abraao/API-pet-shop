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

    @PostMapping
    public ResponseEntity<Agendamento> agendarServico(@RequestBody AgendamentoRequest request) {
        Agendamento agendamento = agendamentoService.agendarServico(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamento);
    }

    // Outros endpoints para atualizar e cancelar agendamentos, bem como obter todos os agendamentos do cliente, por exemplo.
}