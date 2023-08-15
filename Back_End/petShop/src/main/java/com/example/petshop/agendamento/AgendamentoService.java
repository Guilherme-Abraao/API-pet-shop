package com.example.petshop.agendamento;

import com.example.petshop.base.Animal;
import com.example.petshop.base.Cliente;
import com.example.petshop.base.Funcionario;
import com.example.petshop.exception.AgendamentoException;
import com.example.petshop.exception.UserException;
import com.example.petshop.repository.AnimalRepository;
import com.example.petshop.repository.ClienteRepository;
import com.example.petshop.repository.FuncionarioRepository;
import com.example.petshop.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final AnimalRepository animalRepository;
    private final ClienteService clienteService;

//    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
//        this.agendamentoRepository = agendamentoRepository;
//    }

//    public boolean horarioJaAgendado(LocalDateTime horario) {
//        int quantidadeAgendamentos = agendamentoRepository.countByDataHora(horario);
//        return quantidadeAgendamentos > 0;
//    }

    public boolean agendamentoExisteParaFuncionario(
            /*Funcionario funcionario*/Funcionario funcionario, LocalDateTime horario
    ) {
        int quantidadeAgendamentos = agendamentoRepository.countByFuncionarioAndDataHora(funcionario.getId(), horario);
        return quantidadeAgendamentos > 0;
    }

    private Funcionario selecionarFuncionarioAleatorio(List<Funcionario> funcionarios) {
        Random random = new Random();
        int index = random.nextInt(funcionarios.size());
        return funcionarios.get(index);
    }


    public List<Agendamento> agendarServicos(
            List<AgendamentoRequest> requests
    ) throws UserException {
        List<Agendamento> agendamentos = new ArrayList<>();

        for (AgendamentoRequest request : requests) {
            LocalDateTime horario = request.getDataHoraStart();


            Cliente cliente = clienteRepository.findById(request.getClienteId()).orElseThrow();

            Animal animal = animalRepository.findById(request.getAnimalId()).orElseThrow();

//            Funcionario funcionario = funcionarioRepository.findById(request.getFuncionarioId()).orElseThrow();
//            if (agendamentoExisteParaFuncionario(funcionario, horario)) {
//                throw new AgendamentoException("O funcionário já possui um agendamento neste horário.");
//            }

            List<Funcionario> funcionariosDisponiveis = funcionarioRepository.findFuncionariosDisponiveis(horario);

            if (funcionariosDisponiveis.isEmpty()) {
                throw new AgendamentoException("Não há funcionários disponíveis neste horário.");
            }

            Funcionario funcionarioSelecionado = selecionarFuncionarioAleatorio(funcionariosDisponiveis);

            if (agendamentoExisteParaFuncionario(funcionarioSelecionado, horario)) {
                throw new AgendamentoException("O funcionário já possui um agendamento neste horário.");
            }

            // Crie um objeto Agendamento a partir dos dados da requisição
            Agendamento agendamento = new Agendamento();
            agendamento.setCliente(cliente);
            agendamento.setFuncionario(funcionarioSelecionado);
            agendamento.setServicos(request.getServicos());
            agendamento.setAnimal(animal);
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

    public List<Agendamento> getAgendamentosByCliente(Cliente cliente) {
        return agendamentoRepository.findClienteById(cliente.getId());
    }
}

