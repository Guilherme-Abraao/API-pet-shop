package com.example.petshop.funcionario;

import com.example.petshop.usuarios.Usuario;
import com.example.petshop.usuarios.UsuarioRepository;
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

    public void adicionarNovoFuncionario(Funcionario funcionario) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findUsuarioByEmail(funcionario.getEmail());
        if (funcionarioOptional.isPresent()) {
            throw new IllegalStateException("email já existe");
        }
        funcionarioRepository.save(funcionario);

    }

    public void deleteFuncionario(Long funcionarioId) {
        boolean exists = funcionarioRepository.existsById(funcionarioId);
        if (!exists) {
            throw new IllegalStateException("Funcionário com id " + " já existe.");
        }
        funcionarioRepository.deleteById(funcionarioId);
    }

    @Transactional
    public void atualizarFuncionario(Long funcionarioId, String nome, String email) {
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
            Optional<Funcionario> funcionarioOptional = funcionarioRepository.findUsuarioByEmail(email);
            if (funcionarioOptional.isPresent()) {
                throw new IllegalStateException("Email já existe.");
            }
            funcionario.setEmail(email);
        }
    }
}
