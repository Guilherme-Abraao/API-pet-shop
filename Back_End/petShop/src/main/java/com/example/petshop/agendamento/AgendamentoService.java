package com.example.petshop.agendamento;

import com.example.petshop.base.Funcionario;
import com.example.petshop.exception.AgendamentoException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Agendamento agendarServico(AgendamentoRequest request) {

        LocalDateTime horario = request.getDataHora();
        Funcionario funcionario = request.getFuncionario();

//        if (horarioJaAgendado(horario)) {
//            throw new AgendamentoException("O horário já está agendado.");
//        }

        if (agendamentoExisteParaFuncionario(funcionario, horario)) {
            throw new AgendamentoException("O funcionário já possui um agendamento neste horário.");
        }

        // Crie um objeto Agendamento a partir dos dados da requisição
        Agendamento agendamento = new Agendamento();
        agendamento.setCliente(request.getCliente());
        agendamento.setFuncionario(request.getFuncionario());
        agendamento.setServicos(request.getServicos());
        agendamento.setAnimal(request.getAnimal());
        agendamento.setDataHora(request.getDataHora());
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

