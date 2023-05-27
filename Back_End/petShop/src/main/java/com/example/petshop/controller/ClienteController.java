package com.example.petshop.controller;

import com.example.petshop.base.Cliente;
import com.example.petshop.base.Cliente;
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
    @GetMapping(path = "/find/{id}")
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
    }

}
