package com.example.petshop.usuarios;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public void adicionarNovoUsuario(Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findUsuarioByEmail(usuario.getEmail());
        if (usuarioOptional.isPresent()) {
            throw new IllegalStateException("email já existe");
        }
        usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long usuarioId) {
        boolean exists = usuarioRepository.existsById(usuarioId);
        if (!exists) {
            throw new IllegalStateException("Usuário com id " + usuarioId + " não existe.");
        }
        usuarioRepository.deleteById(usuarioId);
    }

    @Transactional
    public void atualizarUsuario(Long usuarioId, String nome, String email) {
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
    }
}
