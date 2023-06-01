package com.example.petshop.service;

import com.example.petshop.base.Funcionario;
import com.example.petshop.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public Funcionario adicionarFuncionario(Funcionario funcionario) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findFuncionarioByEmail(funcionario.getEmail());
        if (funcionarioOptional.isPresent()) {
            throw new IllegalStateException("email já existe");
        }
        return funcionarioRepository.save(funcionario);
    }

    public void deleteFuncionario(Long funcionarioId) {
        boolean exists = funcionarioRepository.existsById(funcionarioId);
        if (!exists) {
            throw new IllegalStateException("Funcionário com id " + funcionarioId + " não existe.");
        }
        funcionarioRepository.deleteById(funcionarioId);
    }

    public Funcionario findFuncionarioById(Long id) {
        return funcionarioRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Funcionário com id " + id + " não existe."
                ));
    }

    @Transactional
    public Funcionario atualizarFuncionario(Long funcionarioId, String nome, String email) {
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new IllegalStateException(
                        "Funcionário com id " + funcionarioId + " não existe."
                ));

        if (nome != null &&
                email.length() > 0 &&
                !Objects.equals(funcionario.getNome(), nome)) {
            funcionario.setNome(nome);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(funcionario.getEmail(), email)) {
            Optional<Funcionario> funcionarioOptional = funcionarioRepository.findFuncionarioByEmail(email);
            if (funcionarioOptional.isPresent()) {
                throw new IllegalStateException("Email já existe.");
            }
            funcionario.setEmail(email);
        }
        return funcionarioRepository.save(funcionario);
    }
}
