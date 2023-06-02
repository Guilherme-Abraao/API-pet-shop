package com.example.petshop.service;

import com.example.petshop.base.Cliente;
import com.example.petshop.exception.UserNotFoundException;
import com.example.petshop.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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


    public Cliente adicionarCliente(Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findClienteByEmail(cliente.getEmail());
        if (clienteOptional.isPresent()) {
            throw new IllegalStateException("email já existe");
        }
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long clienteId) throws UserNotFoundException {
        boolean exists = clienteRepository.existsById(clienteId);
        if (!exists) {
            throw new UserNotFoundException("Cliente com id " + clienteId + " não existe.");
        }
        clienteRepository.deleteById(clienteId);
    }

    public Cliente findClienteById(Long id) throws UserNotFoundException {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "Cliente com id " + id + " não existe."
                ));
    }

    @Transactional
    public Cliente atualizarCliente(Long clienteId, String nome, String email) throws UserNotFoundException {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new UserNotFoundException(
                        "Cliente com id " + clienteId + " não existe."
                ));

        if (nome != null &&
                email.length() > 0 &&
                !Objects.equals(cliente.getNome(), nome)) {
            cliente.setNome(nome);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(cliente.getEmail(), email)) {
            Optional<Cliente> clienteOptional = clienteRepository.findClienteByEmail(email);
            if (clienteOptional.isPresent()) {
                throw new IllegalStateException("Email já existe.");
            }
            cliente.setEmail(email);
        }
        return clienteRepository.save(cliente);
    }
}
