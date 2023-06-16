package com.example.petshop.service;

import com.example.petshop.base.Administrador;
import com.example.petshop.repository.AdministradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public Administrador adicionarAdministrador(Administrador funcionario) {
        Optional<Administrador> funcionarioOptional = administradorRepository.findAdministradorByEmail(funcionario.getEmail());
        if (funcionarioOptional.isPresent()) {
            throw new IllegalStateException("email já existe");
        }
        return administradorRepository.save(funcionario);
    }

    public void deleteAdministrador(Long funcionarioId) {
        boolean exists = administradorRepository.existsById(funcionarioId);
        if (!exists) {
            throw new IllegalStateException("Funcionário com id " + funcionarioId + " não existe.");
        }
        administradorRepository.deleteById(funcionarioId);
    }

    public Administrador findAdministradorById(Long id) {
        return administradorRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Funcionário com id " + id + " não existe."
                ));
    }

    @Transactional
    public Administrador atualizarAdministrador(Long funcionarioId, String nome, String email) {
        Administrador funcionario = administradorRepository.findById(funcionarioId)
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
            Optional<Administrador> funcionarioOptional = administradorRepository.findAdministradorByEmail(email);
            if (funcionarioOptional.isPresent()) {
                throw new IllegalStateException("Email já existe.");
            }
            funcionario.setEmail(email);
        }
        return administradorRepository.save(funcionario);
    }
}
