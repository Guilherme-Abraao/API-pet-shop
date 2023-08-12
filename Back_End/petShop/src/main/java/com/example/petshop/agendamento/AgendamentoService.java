package com.example.petshop.agendamento;

import com.example.petshop.base.Animal;
import com.example.petshop.base.Cliente;
import com.example.petshop.base.Funcionario;
import com.example.petshop.exception.AgendamentoException;
import com.example.petshop.repository.AnimalRepository;
import com.example.petshop.repository.ClienteRepository;
import com.example.petshop.repository.FuncionarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final AnimalRepository animalRepository;

//    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
//        this.agendamentoRepository = agendamentoRepository;
//    }

//    public boolean horarioJaAgendado(LocalDateTime horario) {
//        int quantidadeAgendamentos = agendamentoRepository.countByDataHora(horario);
//        return quantidadeAgendamentos > 0;
//    }

    public boolean agendamentoExisteParaFuncionario(Long funcionarioId, LocalDateTime horario) {
        int quantidadeAgendamentos = agendamentoRepository.countByFuncionarioAndDataHora(funcionarioId, horario);
        return quantidadeAgendamentos > 0;
    }

    public List<Agendamento> agendarServicos(
            List<AgendamentoRequest> requests
    ) {
        List<Agendamento> agendamentos = new ArrayList<>();
        Cliente cliente = clienteRepository.findById(requests.get(0).getClienteId()).orElseThrow();
        Funcionario funcionario = funcionarioRepository.findById(requests.get(0).getFuncionarioId()).orElseThrow();
        Animal animal = animalRepository.findById(requests.get(0).getAnimalId()).orElseThrow();

        for (AgendamentoRequest request : requests) {
            LocalDateTime horario = request.getDataHoraStart();
            Long funcionarioId = request.getFuncionarioId();

            if (agendamentoExisteParaFuncionario(funcionarioId, horario)) {
                throw new AgendamentoException("O funcionário já possui um agendamento neste horário.");
            }

            // Crie um objeto Agendamento a partir dos dados da requisição
            Agendamento agendamento = new Agendamento();
            agendamento.setClienteId(request.getClienteId());
            agendamento.setFuncionarioId(request.getFuncionarioId());
            agendamento.setServicos(request.getServicos());
            agendamento.setAnimalId(request.getAnimalId());
            agendamento.setDataHoraStart(request.getDataHoraStart());
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

