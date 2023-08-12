package com.example.petshop.agendamento;

import com.example.petshop.base.Cliente;
import com.example.petshop.exception.BodyException;
import com.example.petshop.exception.UserException;
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
    public ResponseEntity<List<Agendamento>> agendarServicos(
            @RequestBody List<AgendamentoRequest> requests
    ) throws BodyException {
        List<Agendamento> agendamento = agendamentoService.agendarServicos(
                requests
        );
        return new ResponseEntity<>(agendamento, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{agendamentoId}")
    public ResponseEntity<Agendamento> deletarAgendamento(
            @PathVariable("agendamentoId") Long agendamentoId
    ) {
        agendamentoService.deleteAgendamento(agendamentoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}