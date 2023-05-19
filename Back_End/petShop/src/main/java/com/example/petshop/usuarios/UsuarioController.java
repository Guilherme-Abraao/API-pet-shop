package com.example.petshop.usuarios;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/petshop/cliente")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> getUsuarios() {
        return usuarioService.getUsuarios();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionarNovoUsuario(@RequestBody @Valid Usuario usuario) {
        usuarioService.adicionarNovoUsuario(usuario);
    }

    @DeleteMapping(path = "{usuarioId}")
    public void deleteUsuario(@PathVariable("usuarioId") Long usuarioId) {
        usuarioService.deleteUsuario(usuarioId);
    }

    @PutMapping(path = "{usuarioId}")
    public void atualizarUsuario(
            @PathVariable("usuarioId") Long usuarioId,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email
    ) {
        usuarioService.atualizarUsuario(usuarioId, nome, email);
    }

}
