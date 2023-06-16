package com.example.petshop.service;

import com.example.petshop.base.Usuario;
import com.example.petshop.exception.UserNotFoundException;
import com.example.petshop.repository.UsuarioRepository;
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


    public Usuario adicionarUsuario(Usuario usuario) throws UserNotFoundException {
        Optional<Usuario> usuarioEmailOptional = usuarioRepository.findUsuarioByEmail(usuario.getEmail());
        if (usuarioEmailOptional.isPresent()) {
            throw new UserNotFoundException("email já existe");
        }

        Optional<Usuario> usuarioCpfOptional = usuarioRepository.findUsuarioByCpf(usuario.getCpf());
        if (usuarioCpfOptional.isPresent()) {
            throw new UserNotFoundException("O CPF informado já existe.");
        }

        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long usuarioId) throws UserNotFoundException {
        boolean exists = usuarioRepository.existsById(usuarioId);
        if (!exists) {
            throw new UserNotFoundException("Usuario com id " + usuarioId + " não existe.");
        }
        usuarioRepository.deleteById(usuarioId);
    }

    public Usuario findUsuarioById(Long id) throws UserNotFoundException {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "Usuario com id " + id + " não existe."
                ));
    }

    @Transactional
    public Usuario atualizarUsuario(Long usuarioId, String nome, String email) throws UserNotFoundException {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UserNotFoundException(
                        "Usuario com id " + usuarioId + " não existe."
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
                throw new UserNotFoundException("Email já existe.");
            }
            usuario.setEmail(email);
        }
        return usuarioRepository.save(usuario);
    }
}
