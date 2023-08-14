package com.example.petshop.agendamento;

import com.example.petshop.base.Cliente;
//import com.example.petshop.base.EventoCalendario;
import com.example.petshop.base.EventoCalendario;
import com.example.petshop.exception.BodyException;
import com.example.petshop.exception.UserException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @PostMapping //Não sei se temos que manter o path (path = "/agendar")
    public ResponseEntity<List<Agendamento>> agendarServicos(
            @RequestBody List<AgendamentoRequest> requests
    ) throws BodyException {
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

//    @Autowired
//    private AgendamentoRepository agendamentoRepository;

//    @GetMapping("/eventos") // Endpoint para buscar os eventos/agendamentos
//    public ResponseEntity<String> obterEventosCalendario() {
//        List<Agendamento> agendamentos = agendamentoRepository.findAll();
//
//        List<EventoCalendario> eventosCalendario = new ArrayList<>();
//
//        //Associa os atributos do EventoCalendario com os atributos de Agendamento
//        for (Agendamento agendamento : agendamentos) {
//            EventoCalendario evento = new EventoCalendario();
//            evento.setId(agendamento.getId()); //id do agendamento
//            evento.setSubject(agendamento.getAnimal().getNome()); //nome do animal = subject
//            evento.setStartTime(agendamento.getDataHoraStart());
//            evento.setEndTime(agendamento.getDataHoraEnd());
//            evento.setObservacoes("Serviços:" +
//            agendamento.getServicos().toString() +
//            ", Raça:" + agendamento. getAnimal().getRaca() +
//            ", Funcionário:" + agendamento.getFuncionario().getNome() +
//            ", Objetos deixados e outras informações:" +
//            agendamento.getObservacoes() );
//
//            eventosCalendario.add(evento);
//        }
//
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            String urlJson = objectMapper.writeValueAsString(eventosCalendario);
//            return ResponseEntity.ok(urlJson);
//
//        } catch (Exception e) {
//            return ResponseEntity.status(500).body("Erro ao formatar os eventos para o calendário de eventos.");
//        }
//    }
}
