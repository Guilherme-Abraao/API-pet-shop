package com.example.petshop.controller;

import com.example.petshop.base.Cliente;
import com.example.petshop.base.RegisterRequest;
import com.example.petshop.exception.UserException;
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
    public ResponseEntity<Cliente> getClienteById(
            @PathVariable("id") Long id
    ) throws UserException {
        Cliente cliente = clienteService.findClienteById(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

//    Login
    @GetMapping(path = "/{email}/{senha}")
    public ResponseEntity<Cliente> login(
            @PathVariable("email") String email,
            @PathVariable("senha") String senha
    ) throws UserException {
        Cliente cliente = clienteService.login(email, senha);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    /*
    @PostMapping(path = "/{email}/{senha}")
    public ResponseEntity<Cliente> login(
            @RequestBody LoginRequest loginRequest,
            @PathVariable("email") String email,
            @PathVariable("senha") String senha
    ) throws UserException {
        Cliente cliente = clienteService.login(loginRequest.setEmail(email), loginRequest.setSenha(senha));
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
    * */

//    Cadastrar um cliente
    @PostMapping(path = "/cadastrarCliente")
    public ResponseEntity<Cliente> adicionarCliente(
            @RequestBody @Valid RegisterRequest registerRequest
            ) throws UserException {
        Cliente novoCliente = clienteService.adicionarCliente(registerRequest);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

//    Deletar um cliente
    @DeleteMapping(path = "/deletarCliente/{clienteId}")
    public ResponseEntity<Cliente> deleteCliente(
            @PathVariable("clienteId") Long ClienteId
    ) throws UserException {
        clienteService.deleteCliente(ClienteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    Atualizar um cliente
    @PutMapping(path = "/atualizarCliente/{clienteId}")
    public ResponseEntity<Cliente> atualizarCliente(
            @PathVariable("clienteId") Long clienteId,
            @RequestBody RegisterRequest registerRequest
    ) throws UserException {
        Cliente novoCliente = clienteService.atualizarCliente(clienteId, registerRequest);
        return new ResponseEntity<>(novoCliente, HttpStatus.OK);
    }

}
