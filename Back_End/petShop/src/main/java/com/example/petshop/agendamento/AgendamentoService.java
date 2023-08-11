package com.example.petshop.agendamento;

import com.example.petshop.base.Funcionario;
import com.example.petshop.exception.AgendamentoException;
import com.example.petshop.exception.BodyException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

//    public boolean horarioJaAgendado(LocalDateTime horario) {
//        int quantidadeAgendamentos = agendamentoRepository.countByDataHora(horario);
//        return quantidadeAgendamentos > 0;
//    }

    public boolean agendamentoExisteParaFuncionario(Funcionario funcionario, LocalDateTime horario) {
        int quantidadeAgendamentos = agendamentoRepository.countByFuncionarioAndDataHora(funcionario, horario);
        return quantidadeAgendamentos > 0;
    }

    public List<Agendamento> agendarServicos(List<AgendamentoRequest> requests) {
        List<Agendamento> agendamentos = new ArrayList<>();

        for (AgendamentoRequest request : requests) {
            LocalDateTime horario = request.getDataHora();
            Funcionario funcionario = request.getFuncionario();

            if (agendamentoExisteParaFuncionario(funcionario, horario)) {
                throw new AgendamentoException("O funcionário já possui um agendamento neste horário.");
            }

            // Crie um objeto Agendamento a partir dos dados da requisição
            Agendamento agendamento = new Agendamento();
            agendamento.setCliente(request.getCliente());
            agendamento.setFuncionario(request.getFuncionario());
            agendamento.setServicos(request.getServicos());
            agendamento.setAnimal(request.getAnimal());
            agendamento.setDataHoraStart(request.getDataHora());
            agendamento.setObservacoes(request.getObservacoes());

            agendamentos.add(agendamento);

        }

        // Salve o agendamento no banco de dados
        return agendamentoRepository.saveAll(agendamentos);
    }

    public Agendamento obterAgendamentoPorId(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new AgendamentoException(
                        "Agendamento com id " + id + " não existe."
                ));
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentoRepository.findAll();
    }

    public void deleteAgendamento(Long agendamentoId) throws AgendamentoException {
        boolean exists = agendamentoRepository.existsById(agendamentoId);

        if (!exists) {
            throw new AgendamentoException("Agendamento com id " + agendamentoId + " não existe.");
        }

        agendamentoRepository.deleteById(agendamentoId);
    }
}

