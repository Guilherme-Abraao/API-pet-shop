package com.example.petshop.controller;

import com.example.petshop.base.Usuario;
import com.example.petshop.dto.UsuarioDTO;
import com.example.petshop.exception.UserNotFoundException;
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
    public ResponseEntity<List<UsuarioDTO>> getAllUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.getUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    //    Encontrar apenas um usuário
    @GetMapping(path = "/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable("id") Long id) throws UserNotFoundException {
        UsuarioDTO usuario = usuarioService.findUsuarioById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> adicionarUsuario(@RequestBody @Valid Usuario usuario) throws UserNotFoundException {
        Usuario novoUsuario = usuarioService.adicionarUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{usuarioId}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable("usuarioId") Long UsuarioId) throws UserNotFoundException {
        usuarioService.deleteUsuario(UsuarioId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "{usuarioId}")
    public ResponseEntity<Usuario> atualizarUsuario(
            @PathVariable("usuarioId") Long usuarioId,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email
    ) throws UserNotFoundException {
        Usuario novoUsuario = usuarioService.atualizarUsuario(usuarioId, nome, email);
        return new ResponseEntity<>(novoUsuario, HttpStatus.OK);
    }

}
