package com.example.petshop.controller;

import com.example.petshop.base.Usuario;
import com.example.petshop.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/petshop/cliente")
public class ClienteController {

    private final ClienteService usuarioService;

    @Autowired
    public ClienteController(ClienteService usuarioService) {
        this.usuarioService = usuarioService;
    }

//    Encontrar todos os usuários
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

//    Encontrar apenas um usuário
    @GetMapping(path = "/find/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Long id) {
        Usuario usuario = usuarioService.findUsuarioById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> adicionarUsuario(@RequestBody @Valid Usuario usuario) {
        Usuario novoUsuario = usuarioService.adicionarUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{usuarioId}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable("usuarioId") Long usuarioId) {
        usuarioService.deleteUsuario(usuarioId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "{usuarioId}")
    public ResponseEntity<Usuario> atualizarUsuario(
            @PathVariable("usuarioId") Long usuarioId,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email
    ) {
        Usuario novoUsuario = usuarioService.atualizarUsuario(usuarioId, nome, email);
        return new ResponseEntity<>(novoUsuario, HttpStatus.OK);
    }

}
