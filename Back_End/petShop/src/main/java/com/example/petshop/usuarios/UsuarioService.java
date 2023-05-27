package com.example.petshop.usuarios;

<<<<<<< HEAD:Back_End/petShop/src/main/java/com/example/petshop/usuarios/UsuarioService.java
=======
import com.example.petshop.base.Usuario;
import com.example.petshop.repository.UsuarioRepository;
>>>>>>> parent of 67731d8... Merge pull request #48 from gilmarUFG/guilherme:Back_End/petShop/src/main/java/com/example/petshop/service/ClienteService.java
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
<<<<<<< HEAD:Back_End/petShop/src/main/java/com/example/petshop/usuarios/UsuarioService.java
    public UsuarioService(UsuarioRepository usuarioRepository) {
=======
    public ClienteService(UsuarioRepository usuarioRepository) {
>>>>>>> parent of 67731d8... Merge pull request #48 from gilmarUFG/guilherme:Back_End/petShop/src/main/java/com/example/petshop/service/ClienteService.java
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

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
    }

    public void deleteUsuario(Long usuarioId) {
        boolean exists = usuarioRepository.existsById(usuarioId);
        if (!exists) {
            throw new IllegalStateException("Usuário com id " + usuarioId + " não existe.");
        }
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
<<<<<<< HEAD:Back_End/petShop/src/main/java/com/example/petshop/usuarios/UsuarioService.java
=======
        return usuarioRepository.save(usuario);
>>>>>>> parent of 67731d8... Merge pull request #48 from gilmarUFG/guilherme:Back_End/petShop/src/main/java/com/example/petshop/service/ClienteService.java
    }
}
