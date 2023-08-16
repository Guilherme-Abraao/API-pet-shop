package com.example.petshop.agendamento;

import com.example.petshop.base.EventoCalendario;
import com.example.petshop.exception.BodyException;
import com.example.petshop.exception.UserException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.time.ZoneId.of;

@RestController
@AllArgsConstructor
@RequestMapping("api/petshop/agendamentos")
@CrossOrigin(origins = "http://localhost:4200")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @GetMapping
    public ResponseEntity<List<Agendamento>> getAllAgendamentos() {
        List<Agendamento> agendamentos = agendamentoService.getAgendamentos();
        return new ResponseEntity<>(agendamentos, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Agendamento> obterAgendamento(@PathVariable Long id) {
        Agendamento agendamento = agendamentoService.getAgendamentoPorId(id);
        return new ResponseEntity<>(agendamento, HttpStatus.OK);
    }

    @PostMapping(path = "/agendar")
    public ResponseEntity<List<Agendamento>> agendarServicos(
            @RequestBody List<AgendamentoRequest> requests
    ) throws BodyException, UserException {
        List<Agendamento> agendamento = agendamentoService.agendarServicos(requests);
        return new ResponseEntity<>(agendamento, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{agendamentoId}")
    public ResponseEntity<Agendamento> deletarAgendamento(
            @PathVariable("agendamentoId") Long agendamentoId
    ) {
        agendamentoService.deleteAgendamento(agendamentoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

   @GetMapping(path = {"/eventos", "/eventos/"})
   public ResponseEntity<List<EventoCalendario>> obterEventosCalendario() {

        List<EventoCalendario> eventosCalendario = agendamentoService.getEventosCalendario();

        return ResponseEntity.ok(eventosCalendario);
  }
  
}
