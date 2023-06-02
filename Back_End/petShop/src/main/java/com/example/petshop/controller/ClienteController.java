package com.example.petshop.controller;

import com.example.petshop.base.Cliente;
import com.example.petshop.exception.UserNotFoundException;
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

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

//    Encontrar todos os usuários
    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteService.getClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

//    Encontrar apenas um usuário
    @GetMapping(path = "/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long id) throws UserNotFoundException {
        Cliente cliente = clienteService.findClienteById(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> adicionarCliente(@RequestBody @Valid Cliente cliente) {
        Cliente novoCliente = clienteService.adicionarCliente(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{clienteId}")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable("clienteId") Long ClienteId) throws UserNotFoundException {
        clienteService.deleteCliente(ClienteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "{clienteId}")
    public ResponseEntity<Cliente> atualizarCliente(
            @PathVariable("clienteId") Long clienteId,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email
    ) throws UserNotFoundException {
        Cliente novoCliente = clienteService.atualizarCliente(clienteId, nome, email);
        return new ResponseEntity<>(novoCliente, HttpStatus.OK);
    }

}
