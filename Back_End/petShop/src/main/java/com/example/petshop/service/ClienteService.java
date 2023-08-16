package com.example.petshop.service;

import com.example.petshop.base.Cliente;
import com.example.petshop.base.RegisterRequest;
import com.example.petshop.exception.UserException;
import com.example.petshop.exception.UserNotFoundException;
import com.example.petshop.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.petshop.base.Role.USER;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    public Cliente getClienteById(Long id) throws UserNotFoundException {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "Cliente com id " + id + " não existe."
                ));
    }

    public Cliente adicionarCliente(RegisterRequest registerRequest) throws UserException {
        Optional<Cliente> clienteEmailOptional = clienteRepository.findClienteByEmail(registerRequest.getEmail());
        if (clienteEmailOptional.isPresent()) {
            throw new UserException("O email informado já existe");
        }

        Optional<Cliente> clienteCpfOptional = clienteRepository.findClienteByCpf(registerRequest.getCpf());
        if (clienteCpfOptional.isPresent()) {
            throw new UserException("O CPF informado já existe.");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(registerRequest.getNome());
        cliente.setEmail(registerRequest.getEmail());
        cliente.setCpf(registerRequest.getCpf());
        cliente.setTelefone(registerRequest.getTelefone());
        cliente.setSenha(registerRequest.getSenha());
        cliente.setDataNascimento(registerRequest.getDataNascimento());
        cliente.setRole(USER);

        return clienteRepository.save(cliente);
    }

    public Cliente login(String email, String senha) throws UserException {

        Cliente cliente = clienteRepository.findClienteByEmail(email)
                .orElseThrow(() -> new UserException("Email não encontrado."));

        if (!Objects.equals(cliente.getSenha(), senha)) {
            throw new UserException("Senha incorreta.");
        }

        return cliente;
    }

    public void deleteCliente(Long clienteId) throws UserNotFoundException {
        boolean exists = clienteRepository.existsById(clienteId);
        if (!exists) {
            throw new UserNotFoundException("Cliente com id " + clienteId + " não existe.");
        }
        clienteRepository.deleteById(clienteId);
    }

    @Transactional
    public Cliente atualizarCliente(Long clienteId, RegisterRequest registerRequest) throws UserNotFoundException, UserException {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new UserNotFoundException(
                        "Cliente com id " + clienteId + " não existe."
                ));

        String nome = registerRequest.getNome();
        String email = registerRequest.getEmail();
        String cpf = registerRequest.getCpf();
        String telefone = registerRequest.getTelefone();
        String senha = registerRequest.getSenha();
        LocalDate dataNascimento = registerRequest.getDataNascimento();

        if (nome != null && !nome.isEmpty()) {
            cliente.setNome(nome);
        }

        if (email != null && !email.isEmpty()) {
            Optional<Cliente> clienteOptional = clienteRepository.findClienteByEmail(email);
            if (clienteOptional.isPresent() && !clienteOptional.get().getId().equals(clienteId)) {
                throw new UserException("Email já existe.");
            }
            cliente.setEmail(email);
        }

        if (cpf != null && !cpf.isEmpty()) {
            Optional<Cliente> clienteOptional = clienteRepository.findClienteByCpf(cpf);
            if (clienteOptional.isPresent() && !clienteOptional.get().getId().equals(clienteId)) {
                throw new UserException("CPF já existe.");
            }
            cliente.setCpf(cpf);
        }

        if (telefone != null && !telefone.isEmpty()) {
            cliente.setTelefone(telefone);
        }

        if (senha != null && !senha.isEmpty()) {
            cliente.setSenha(senha);
        }

        if (dataNascimento != null) {
            cliente.setDataNascimento(dataNascimento);
        }

        return clienteRepository.save(cliente);
    }
}
