package com.example.petshop.service;

import com.example.petshop.base.Cliente;
import com.example.petshop.base.CliRequest;
import com.example.petshop.exception.UserException;
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

    public Cliente findClienteById(Long id) throws UserException {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new UserException(
                        "Cliente com id " + id + " não existe."
                ));
    }

    public Cliente adicionarCliente(CliRequest cliRequest) throws UserException {
        Optional<Cliente> clienteEmailOptional = clienteRepository.findClienteByEmail(cliRequest.getEmail());
        if (clienteEmailOptional.isPresent()) {
            throw new UserException("O email informado já existe");
        }

        Optional<Cliente> clienteCpfOptional = clienteRepository.findClienteByCpf(cliRequest.getCpf());
        if (clienteCpfOptional.isPresent()) {
            throw new UserException("O CPF informado já existe.");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(cliRequest.getNome());
        cliente.setEmail(cliRequest.getEmail());
        cliente.setCpf(cliRequest.getCpf());
        cliente.setTelefone(cliRequest.getTelefone());
        cliente.setSenha(cliRequest.getSenha());
        cliente.setDataNascimento(cliRequest.getDataNascimento());
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
    public Cliente atualizarCliente(Long clienteId, CliRequest cliRequest) throws UserException {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new UserException(
                        "Cliente com id " + clienteId + " não existe."
                ));

        String nome = cliRequest.getNome();
        String email = cliRequest.getEmail();
        String cpf = cliRequest.getCpf();
        String telefone = cliRequest.getTelefone();
        String senha = cliRequest.getSenha();
        LocalDate dataNascimento = cliRequest.getDataNascimento();

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
