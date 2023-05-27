package com.example.petshop.service;

<<<<<<< HEAD:Back_End/petShop/src/main/java/com/example/petshop/usuarios/UsuarioService.java
<<<<<<< HEAD:Back_End/petShop/src/main/java/com/example/petshop/usuarios/UsuarioService.java
=======
import com.example.petshop.base.Usuario;
import com.example.petshop.repository.UsuarioRepository;
>>>>>>> parent of 67731d8... Merge pull request #48 from gilmarUFG/guilherme:Back_End/petShop/src/main/java/com/example/petshop/service/ClienteService.java
=======
import com.example.petshop.base.Cliente;
import com.example.petshop.repository.ClienteRepository;
>>>>>>> main:Back_End/petShop/src/main/java/com/example/petshop/service/ClienteService.java
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
<<<<<<< HEAD:Back_End/petShop/src/main/java/com/example/petshop/usuarios/UsuarioService.java
<<<<<<< HEAD:Back_End/petShop/src/main/java/com/example/petshop/usuarios/UsuarioService.java
    public UsuarioService(UsuarioRepository usuarioRepository) {
=======
    public ClienteService(UsuarioRepository usuarioRepository) {
>>>>>>> parent of 67731d8... Merge pull request #48 from gilmarUFG/guilherme:Back_End/petShop/src/main/java/com/example/petshop/service/ClienteService.java
        this.usuarioRepository = usuarioRepository;
=======
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
>>>>>>> main:Back_End/petShop/src/main/java/com/example/petshop/service/ClienteService.java
    }

    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

<<<<<<< HEAD:Back_End/petShop/src/main/java/com/example/petshop/usuarios/UsuarioService.java
<<<<<<< HEAD:Back_End/petShop/src/main/java/com/example/petshop/usuarios/UsuarioService.java
    public void adicionarNovoUsuario(Usuario usuario) {
=======
    public Usuario adicionarUsuario(Usuario usuario) {
>>>>>>> parent of 67731d8... Merge pull request #48 from gilmarUFG/guilherme:Back_End/petShop/src/main/java/com/example/petshop/service/ClienteService.java
        Optional<Usuario> usuarioOptional = usuarioRepository.findUsuarioByEmail(usuario.getEmail());
        if (usuarioOptional.isPresent()) {
            throw new IllegalStateException("email já existe");
        }
<<<<<<< HEAD:Back_End/petShop/src/main/java/com/example/petshop/usuarios/UsuarioService.java
        usuarioRepository.save(usuario);
=======
        return usuarioRepository.save(usuario);
>>>>>>> parent of 67731d8... Merge pull request #48 from gilmarUFG/guilherme:Back_End/petShop/src/main/java/com/example/petshop/service/ClienteService.java
=======

    public Cliente adicionarCliente(Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findClienteByEmail(cliente.getEmail());
        if (clienteOptional.isPresent()) {
            throw new IllegalStateException("email já existe");
        }
        return clienteRepository.save(cliente);
>>>>>>> main:Back_End/petShop/src/main/java/com/example/petshop/service/ClienteService.java
    }

    public void deleteCliente(Long usuarioId) {
        boolean exists = clienteRepository.existsById(usuarioId);
        if (!exists) {
            throw new IllegalStateException("Cliente com id " + usuarioId + " não existe.");
        }
<<<<<<< HEAD:Back_End/petShop/src/main/java/com/example/petshop/usuarios/UsuarioService.java
        usuarioRepository.deleteById(usuarioId);
<<<<<<< HEAD:Back_End/petShop/src/main/java/com/example/petshop/usuarios/UsuarioService.java
    }

    @Transactional
    public void atualizarUsuario(Long usuarioId, String nome, String email) {
=======
    }

    public Usuario findUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Usuário com id " + id + " não existe."
                ));
    }

    @Transactional
    public Usuario atualizarUsuario(Long usuarioId, String nome, String email) {
>>>>>>> parent of 67731d8... Merge pull request #48 from gilmarUFG/guilherme:Back_End/petShop/src/main/java/com/example/petshop/service/ClienteService.java
        Usuario usuario = usuarioRepository.findById(usuarioId)
=======
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
>>>>>>> main:Back_End/petShop/src/main/java/com/example/petshop/service/ClienteService.java
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
<<<<<<< HEAD:Back_End/petShop/src/main/java/com/example/petshop/usuarios/UsuarioService.java
<<<<<<< HEAD:Back_End/petShop/src/main/java/com/example/petshop/usuarios/UsuarioService.java
=======
        return usuarioRepository.save(usuario);
>>>>>>> parent of 67731d8... Merge pull request #48 from gilmarUFG/guilherme:Back_End/petShop/src/main/java/com/example/petshop/service/ClienteService.java
=======
        return clienteRepository.save(cliente);
>>>>>>> main:Back_End/petShop/src/main/java/com/example/petshop/service/ClienteService.java
    }
}
