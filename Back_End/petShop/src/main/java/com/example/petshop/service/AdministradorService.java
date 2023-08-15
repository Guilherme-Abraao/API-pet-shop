package com.example.petshop.service;

import com.example.petshop.base.Administrador;
import com.example.petshop.base.Cargo;
import com.example.petshop.base.CliRequest;
import com.example.petshop.base.EmployeeRequest;
import com.example.petshop.exception.UserException;
import com.example.petshop.repository.AdministradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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

    public Administrador adicionarAdministrador(EmployeeRequest employeeRequest) throws UserException {
        Optional<Administrador> administradorOptional = administradorRepository.findAdministradorByEmail(employeeRequest.getEmail());
        if (administradorOptional.isPresent()) {
            throw new UserException("O email informado já existe");
        }

        Administrador admin = new Administrador();
        admin.setNome(employeeRequest.getNome());
        admin.setEmail(employeeRequest.getEmail());
        admin.setCpf(employeeRequest.getCpf());
        admin.setTelefone(employeeRequest.getTelefone());
        admin.setSenha(employeeRequest.getSenha());
        admin.setDataNascimento(employeeRequest.getDataNascimento());
        admin.setRole(ADMIN);
        admin.setCargo(employeeRequest.getCargo());
        admin.setSalario(employeeRequest.getSalario());

        return administradorRepository.save(admin);
    }

    public void deleteAdministrador(Long administradorId) throws UserException {
        boolean exists = administradorRepository.existsById(administradorId);
        if (!exists) {
            throw new UserException("Funcionário com id " + administradorId + " não existe.");
        }
        administradorRepository.deleteById(administradorId);
    }

    public Administrador findAdministradorById(Long id) throws UserException {
        return administradorRepository.findById(id)
                .orElseThrow(() -> new UserException(
                        "Funcionário com id " + id + " não existe."
                ));
    }

    @Transactional
    public Administrador atualizarAdministrador(
            Long administradorId,
            EmployeeRequest employeeRequest
    ) throws UserException {
        Administrador administrador = administradorRepository.findById(administradorId)
                .orElseThrow(() -> new UserException(
                        "Administrador com id " + administradorId + " não existe."
                ));

        String nome = employeeRequest.getNome();
        String email = employeeRequest.getEmail();
        String cpf = employeeRequest.getCpf();
        String telefone = employeeRequest.getTelefone();
        String senha = employeeRequest.getSenha();
        LocalDate dataNascimento = employeeRequest.getDataNascimento();
        Cargo cargo = employeeRequest.getCargo();
        Double salario = employeeRequest.getSalario();


        if (nome != null && !nome.isEmpty()) {
            administrador.setNome(nome);
        }

        if (email != null && !email.isEmpty()) {
            Optional<Administrador> administradorOptional = administradorRepository.findAdministradorByEmail(email);
            if (administradorOptional.isPresent() && !administradorOptional.get().getId().equals(administradorId)) {
                throw new UserException("Email já existe.");
            }
            administrador.setEmail(email);
        }

        if (cpf != null && !cpf.isEmpty()) {
            Optional<Administrador> administradorOptional = administradorRepository.findAdministradorByCpf(cpf);
            if (administradorOptional.isPresent() && !administradorOptional.get().getId().equals(administradorId)) {
                throw new UserException("CPF já existe.");
            }
            administrador.setCpf(cpf);
        }

        if (telefone != null && !telefone.isEmpty()) {
            administrador.setTelefone(telefone);
        }

        if (senha != null && !senha.isEmpty()) {
            administrador.setSenha(senha);
        }

        if (dataNascimento != null) {
            administrador.setDataNascimento(dataNascimento);
        }

        if (cargo != null) {
            administrador.setCargo(cargo);
        }

        if (salario != null) {
            administrador.setSalario(salario);
        }

        return administradorRepository.save(administrador);
    }
}
