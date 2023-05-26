package com.example.petshop.service;

import com.example.petshop.base.Usuario;
import com.example.petshop.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public ClienteService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario adicionarUsuario(Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findUsuarioByEmail(usuario.getEmail());
        if (usuarioOptional.isPresent()) {
            throw new IllegalStateException("email já existe");
        }
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long usuarioId) {
        boolean exists = usuarioRepository.existsById(usuarioId);
        if (!exists) {
            throw new IllegalStateException("Usuário com id " + usuarioId + " não existe.");
        }
        usuarioRepository.deleteById(usuarioId);
    }

    public Usuario findUsuarioById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Usuário com id " + id + " não existe."
                ));
    }

    @Transactional
    public Usuario atualizarUsuario(Long usuarioId, String nome, String email) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalStateException(
                        "Usuário com id " + usuarioId + " não existe."
                ));

        if (nome != null &&
                email.length() > 0 &&
                !Objects.equals(usuario.getNome(), nome)) {
            usuario.setNome(nome);
        }

        if (email != null &&
                email.length() > 0 &&
                !Objects.equals(usuario.getEmail(), email)) {
            Optional<Usuario> usuarioOptional = usuarioRepository.findUsuarioByEmail(email);
            if (usuarioOptional.isPresent()) {
                throw new IllegalStateException("Email já existe.");
            }
            usuario.setEmail(email);
        }
        return usuarioRepository.save(usuario);
    }
}
