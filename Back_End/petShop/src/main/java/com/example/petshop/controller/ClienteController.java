package com.example.petshop.controller;

<<<<<<< HEAD
import com.example.petshop.base.Usuario;
=======
import com.example.petshop.base.Cliente;
import com.example.petshop.base.Cliente;
>>>>>>> main
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

<<<<<<< HEAD
    private final ClienteService usuarioService;

    @Autowired
    public ClienteController(ClienteService usuarioService) {
        this.usuarioService = usuarioService;
=======
    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
>>>>>>> main
    }

//    Encontrar todos os usuários
    @GetMapping
<<<<<<< HEAD
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
=======
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteService.getClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

//    Encontrar apenas um usuário
    @GetMapping(path = "/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long id) {
        Cliente cliente = clienteService.findClienteById(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> adicionarCliente(@RequestBody @Valid Cliente cliente) {
        Cliente novoCliente = clienteService.adicionarCliente(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{ClienteId}")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable("ClienteId") Long ClienteId) {
        clienteService.deleteCliente(ClienteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "{ClienteId}")
    public ResponseEntity<Cliente> atualizarCliente(
            @PathVariable("ClienteId") Long clienteId,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email
    ) {
        Cliente novoCliente = clienteService.atualizarCliente(clienteId, nome, email);
        return new ResponseEntity<>(novoCliente, HttpStatus.OK);
>>>>>>> main
    }

}
