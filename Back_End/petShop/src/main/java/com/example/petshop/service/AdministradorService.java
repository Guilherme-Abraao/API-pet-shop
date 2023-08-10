package com.example.petshop.service;

import com.example.petshop.base.Administrador;
import com.example.petshop.base.RegisterRequest;
import com.example.petshop.exception.UserException;
import com.example.petshop.repository.AdministradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.petshop.base.Role.ADMIN;

@Service
public class AdministradorService {

    private final AdministradorRepository administradorRepository;

    @Autowired
    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public List<Administrador> getAdministradores() {
        return administradorRepository.findAll();
    }

    public Administrador adicionarAdministrador(RegisterRequest registerRequest) throws UserException {
        Optional<Administrador> funcionarioOptional = administradorRepository.findAdministradorByEmail(registerRequest.getEmail());
        if (funcionarioOptional.isPresent()) {
            throw new UserException("O email informado já existe");
        }

        Administrador admin = new Administrador();
        admin.setNome(registerRequest.getNome());
        admin.setEmail(registerRequest.getEmail());
        admin.setCpf(registerRequest.getCpf());
        admin.setTelefone(registerRequest.getTelefone());
        admin.setSenha(registerRequest.getSenha());
        admin.setDataNascimento(registerRequest.getDataNascimento());
        admin.setRole(ADMIN);
        admin.setCargo(registerRequest.getCargo());
        admin.setSalario(registerRequest.getSalario());

        return administradorRepository.save(admin);
    }

    public void deleteAdministrador(Long funcionarioId) throws UserException {
        boolean exists = administradorRepository.existsById(funcionarioId);
        if (!exists) {
            throw new UserException("Funcionário com id " + funcionarioId + " não existe.");
        }
        administradorRepository.deleteById(funcionarioId);
    }

    public Administrador findAdministradorById(Long id) throws UserException {
        return administradorRepository.findById(id)
                .orElseThrow(() -> new UserException(
                        "Funcionário com id " + id + " não existe."
                ));
    }

    @Transactional
    public Administrador atualizarAdministrador(
            Long funcionarioId,
            String nome,
            String email
    ) throws UserException {
        Administrador funcionario = administradorRepository.findById(funcionarioId)
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
            Optional<Administrador> funcionarioOptional = administradorRepository.findAdministradorByEmail(email);
            if (funcionarioOptional.isPresent()) {
                throw new UserException("Email já existe.");
            }
            funcionario.setEmail(email);
        }
        return administradorRepository.save(funcionario);
    }
}
