package com.example.petshop.service;

import com.example.petshop.base.Cliente;
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

    public void deleteCliente(Long usuarioId) {
        boolean exists = clienteRepository.existsById(usuarioId);
        if (!exists) {
            throw new IllegalStateException("Cliente com id " + usuarioId + " não existe.");
        }
        clienteRepository.deleteById(usuarioId);
    }

    public Cliente findClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Cliente com id " + id + " não existe."
                ));
    }

    @Transactional
    public Cliente atualizarCliente(Long usuarioId, String nome, String email) {
        Cliente cliente = clienteRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalStateException(
                        "Cliente com id " + usuarioId + " não existe."
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
