package com.example.petshop.service;

import com.example.petshop.base.Funcionario;
import com.example.petshop.base.RegisterRequest;
import com.example.petshop.exception.UserException;
import com.example.petshop.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.petshop.base.Role.FUNC;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Funcionario adicionarFuncionario(RegisterRequest registerRequest) throws UserException {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findFuncionarioByEmail(registerRequest.getEmail());
        if (funcionarioOptional.isPresent()) {
            throw new UserException("O email informado já existe");
        }

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(registerRequest.getNome());
        funcionario.setEmail(registerRequest.getEmail());
        funcionario.setCpf(registerRequest.getCpf());
        funcionario.setTelefone(registerRequest.getTelefone());
        funcionario.setSenha(registerRequest.getSenha());
        funcionario.setDataNascimento(registerRequest.getDataNascimento());
        funcionario.setRole(FUNC);
        funcionario.setCargo(registerRequest.getCargo());
        funcionario.setSalario(registerRequest.getSalario());

        return funcionarioRepository.save(funcionario);
    }

    public void deleteFuncionario(Long funcionarioId) throws UserException {
        boolean exists = funcionarioRepository.existsById(funcionarioId);
        if (!exists) {
            throw new UserException("Funcionário com id " + funcionarioId + " não existe.");
        }
        funcionarioRepository.deleteById(funcionarioId);
    }

    public Funcionario findFuncionarioById(Long id) throws UserException {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new UserException(
                        "Funcionário com id " + id + " não existe."
                ));
    }

    @Transactional
    public Funcionario atualizarFuncionario(
            Long funcionarioId,
            String nome,
            String email
    ) throws UserException {
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new UserException(
                        "Funcionário com id " + funcionarioId + " não existe."
                ));

        if (nome != null &&
                !email.isEmpty() &&
                !Objects.equals(funcionario.getNome(), nome)) {
            funcionario.setNome(nome);
        }

        if (email != null &&
                !email.isEmpty() &&
                !Objects.equals(funcionario.getEmail(), email)) {
            Optional<Funcionario> funcionarioOptional = funcionarioRepository.findFuncionarioByEmail(email);
            if (funcionarioOptional.isPresent()) {
                throw new UserException("Email já existe.");
            }
            funcionario.setEmail(email);
        }
        return funcionarioRepository.save(funcionario);
    }
}
