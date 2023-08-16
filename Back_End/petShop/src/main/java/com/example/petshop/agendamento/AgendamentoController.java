package com.example.petshop.agendamento;

import com.example.petshop.base.EventoCalendario;
import com.example.petshop.exception.BodyException;
import com.example.petshop.exception.UserException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/petshop/agendamentos")
@CrossOrigin(origins = "http://localhost:4200")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;
    private final AgendamentoRepository agendamentoRepository;

    @GetMapping
    public ResponseEntity<List<Agendamento>> getAllAgendamentos() {
        List<Agendamento> agendamentos = agendamentoService.getAgendamentos();
        return new ResponseEntity<>(agendamentos, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Agendamento> obterAgendamento(@PathVariable Long id) {
        Agendamento agendamento = agendamentoService.obterAgendamentoPorId(id);
        return ResponseEntity.ok(agendamento);
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

   @Autowired
   private AgendamentoRepository agendamentoRepository;

   @GetMapping(path = {"/eventos", "/eventos/"})
    public ResponseEntity<List<EventoCalendario>> obterEventosCalendario(
) {

    List<Agendamento> agendamentos;
    agendamentos = agendamentoRepository.findAll();

    List<EventoCalendario> eventosCalendario = new ArrayList<>();

    for (Agendamento agendamento : agendamentos) {
        EventoCalendario evento = new EventoCalendario();
        evento.setId(agendamento.getId());
        evento.setSubject(agendamento.getAnimal().getNome());
        evento.setStartTime(agendamento.getDataHoraStart().toString()+":00"+'Z');
        evento.setEndTime(agendamento.getDataHoraEnd().toString()+":00"+'Z');
        evento.setObservacoes("Serviços: " +
            agendamento.getServicos().toString() +
            ", Raça: " + agendamento.getAnimal().getRaca() +
            ", Funcionário: " + agendamento.getFuncionario().getNome() +
            ", Objetos deixados e outras informações: " +
            agendamento.getObservacoes());

        eventosCalendario.add(evento);
    }

    return ResponseEntity.ok(eventosCalendario);
  }
  
}
