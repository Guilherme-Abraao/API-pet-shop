package com.example.petshop.service;

import com.example.petshop.base.Cargo;
import com.example.petshop.base.Cliente;
import com.example.petshop.base.Funcionario;
import com.example.petshop.base.RegisterRequest;
import com.example.petshop.exception.UserException;
import com.example.petshop.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
            RegisterRequest registerRequest
    ) throws UserException {
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new UserException(
                        "Funcionario com id " + funcionarioId + " não existe."
                ));

        String nome = registerRequest.getNome();
        String email = registerRequest.getEmail();
        String cpf = registerRequest.getCpf();
        String telefone = registerRequest.getTelefone();
        String senha = registerRequest.getSenha();
        LocalDate dataNascimento = registerRequest.getDataNascimento();
        Cargo cargo = registerRequest.getCargo();
        Double salario = registerRequest.getSalario();


        if (nome != null && !nome.isEmpty()) {
            funcionario.setNome(nome);
        }

        if (email != null && !email.isEmpty()) {
            Optional<Funcionario> funcionarioOptional = funcionarioRepository.findFuncionarioByEmail(email);
            if (funcionarioOptional.isPresent() && !funcionarioOptional.get().getId().equals(funcionarioId)) {
                throw new UserException("Email já existe.");
            }
            funcionario.setEmail(email);
        }

        if (cpf != null && !cpf.isEmpty()) {
            Optional<Funcionario> funcionarioOptional = funcionarioRepository.findFuncionarioByCpf(cpf);
            if (funcionarioOptional.isPresent() && !funcionarioOptional.get().getId().equals(funcionarioId)) {
                throw new UserException("CPF já existe.");
            }
            funcionario.setCpf(cpf);
        }

        if (telefone != null && !telefone.isEmpty()) {
            funcionario.setTelefone(telefone);
        }

        if (senha != null && !senha.isEmpty()) {
            funcionario.setSenha(senha);
        }

        if (dataNascimento != null) {
            funcionario.setDataNascimento(dataNascimento);
        }

        if (cargo != null) {
            funcionario.setCargo(cargo);
        }

        if (salario != null) {
            funcionario.setSalario(salario);
        }

        return funcionarioRepository.save(funcionario);
    }

    public Funcionario login(String email, String senha) throws UserException {

        Funcionario funcionario = funcionarioRepository.findFuncionarioByEmail(email)
                .orElseThrow(() -> new UserException("Email não encontrado."));

        if (!Objects.equals(funcionario.getSenha(), senha)) {
            throw new UserException("Senha incorreta.");
        }

        return funcionario;
    }
}
