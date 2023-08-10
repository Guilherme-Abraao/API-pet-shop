package com.example.petshop.service;

import com.example.petshop.base.RegisterRequest;
import com.example.petshop.base.Usuario;
import com.example.petshop.exception.UserException;
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

    public Usuario findUsuarioById(Long id) throws UserException {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UserException(
                        "Usuario com id " + id + " não existe."
                ));
    }

    public Usuario adicionarUsuario(RegisterRequest registerRequest) throws UserException {
        Optional<Usuario> usuarioEmailOptional = usuarioRepository.findUsuarioByEmail(registerRequest.getEmail());
        if (usuarioEmailOptional.isPresent()) {
            throw new UserException("Email já existe");
        }

        Optional<Usuario> usuarioCpfOptional = usuarioRepository.findUsuarioByCpf(registerRequest.getCpf());
        if (usuarioCpfOptional.isPresent()) {
            throw new UserException("O CPF informado já existe.");
        }

        Usuario user = new Usuario();
        user.setNome(registerRequest.getNome());
        user.setEmail(registerRequest.getEmail());
        user.setCpf(registerRequest.getCpf());
        user.setTelefone(registerRequest.getTelefone());
        user.setSenha(registerRequest.getSenha());
        user.setDataNascimento(registerRequest.getDataNascimento());

        return usuarioRepository.save(user);
    }

    public void deleteUsuario(Long usuarioId) throws UserException {
        boolean exists = usuarioRepository.existsById(usuarioId);
        if (!exists) {
            throw new UserException("Usuario com id " + usuarioId + " não existe.");
        }
        usuarioRepository.deleteById(usuarioId);
    }

    @Transactional
    public Usuario atualizarUsuario(Long usuarioId, String nome, String email) throws UserException {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UserException(
                        "Usuario com id " + usuarioId + " não existe."
                ));

        if (nome != null &&
                !email.isEmpty() &&
                !Objects.equals(usuario.getNome(), nome)) {
            usuario.setNome(nome);
        }

        if (email != null &&
                !email.isEmpty() &&
                !Objects.equals(usuario.getEmail(), email)) {
            Optional<Usuario> usuarioOptional = usuarioRepository.findUsuarioByEmail(email);
            if (usuarioOptional.isPresent()) {
                throw new UserException("Email já existe.");
            }
            usuario.setEmail(email);
        }
        return usuarioRepository.save(usuario);
    }
}
