package com.example.petshop.controller;

import com.example.petshop.base.RegisterRequest;
import com.example.petshop.base.Usuario;
import com.example.petshop.exception.UserException;
import com.example.petshop.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/petshop/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //    Encontrar todos os usuários
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    //    Encontrar apenas um usuário
    @GetMapping(path = "/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("id") Long id) throws UserException {
        Usuario usuario = usuarioService.findUsuarioById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> adicionarUsuario(@RequestBody @Valid RegisterRequest registerRequest) throws UserException {
        Usuario novoUsuario = usuarioService.adicionarUsuario(registerRequest);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{usuarioId}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable("usuarioId") Long UsuarioId) throws UserException {
        usuarioService.deleteUsuario(UsuarioId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "{usuarioId}")
    public ResponseEntity<Usuario> atualizarUsuario(
            @PathVariable("usuarioId") Long usuarioId,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email
    ) throws UserException {
        Usuario novoUsuario = usuarioService.atualizarUsuario(usuarioId, nome, email);
        return new ResponseEntity<>(novoUsuario, HttpStatus.OK);
    }

}
