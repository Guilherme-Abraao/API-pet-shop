package com.example.petshop.service;

import com.example.petshop.base.Cliente;
import com.example.petshop.base.RegisterRequest;
import com.example.petshop.exception.UserException;
import com.example.petshop.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Cliente findClienteById(Long id) throws UserException {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new UserException(
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

    public void deleteCliente(Long clienteId) throws UserException {
        boolean exists = clienteRepository.existsById(clienteId);
        if (!exists) {
            throw new UserException("Cliente com id " + clienteId + " não existe.");
        }
        clienteRepository.deleteById(clienteId);
    }

    @Transactional
    public Cliente atualizarCliente(Long clienteId, String nome, String email) throws UserException {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new UserException(
                        "Cliente com id " + clienteId + " não existe."
                ));

        if (nome != null &&
                !email.isEmpty() &&
                !Objects.equals(cliente.getNome(), nome)) {
            cliente.setNome(nome);
        }

        if (email != null &&
                !email.isEmpty() &&
                !Objects.equals(cliente.getEmail(), email)) {
            Optional<Cliente> clienteOptional = clienteRepository.findClienteByEmail(email);
            if (clienteOptional.isPresent()) {
                throw new UserException("Email já existe.");
            }
            cliente.setEmail(email);
        }
        return clienteRepository.save(cliente);
    }
}
