package com.example.petshop.agendamento;

import com.example.petshop.exception.HorarioJaAgendadoException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public boolean horarioJaAgendado(LocalDateTime horario) {
        int quantidadeAgendamentos = agendamentoRepository.countByDataHora(horario);
        return quantidadeAgendamentos > 0;
    }

    public Agendamento agendarServico(AgendamentoRequest request) {

        LocalDateTime horario = request.getDataHora();

        if (horarioJaAgendado(horario)) {
            throw new HorarioJaAgendadoException("O horário já está agendado.");
        }

        // Crie um objeto Agendamento a partir dos dados da requisição
        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(request.getCliente());
        agendamento.setServico(request.getServico());
        agendamento.setAnimal(request.getAnimal());
        agendamento.setDataHora(request.getDataHora());
        agendamento.setFuncionario(request.getFuncionario());
        agendamento.setObservacoes(request.getObservacoes());

        // Salve o agendamento no banco de dados
        return agendamentoRepository.save(agendamento);
    }

    public Agendamento obterAgendamentoPorId(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Agendamento com id " + id + " não existe."
                ));
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentoRepository.findAll();
    }

    // Implemente outros métodos de serviço conforme necessário, como atualizar e cancelar agendamentos, obter todos os agendamentos de um cliente, etc.
}

