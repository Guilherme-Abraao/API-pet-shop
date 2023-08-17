package com.example.petshop.controller;

import com.example.petshop.agendamento.Agendamento;
import com.example.petshop.agendamento.AgendamentoService;
import com.example.petshop.base.Cliente;
import com.example.petshop.base.RegisterRequest;
import com.example.petshop.exception.UserException;
import com.example.petshop.exception.UserNotFoundException;
import com.example.petshop.service.ClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(path = "api/petshop/cliente")
public class ClienteController {

    private final ClienteService clienteService;
    private final AgendamentoService agendamentoService;

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
    ) throws UserNotFoundException {
        Cliente cliente = clienteService.getClienteById(id);
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

//    Histórico de Agendamentos
    @GetMapping(path = "/agendamentos/{clienteId}")
    public ResponseEntity<List<Agendamento>> getAgendamentos(
            @PathVariable("clienteId") Long clienteId
    ) {
        try {
            Cliente cliente = clienteService.getClienteById(clienteId);
            List<Agendamento> agendamentos = agendamentoService.getAgendamentosByCliente(cliente);
            return new ResponseEntity<>(agendamentos, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

//    Cadastrar um cliente
    @PostMapping(path = "/cadastrarCliente")
    public ResponseEntity<Cliente> adicionarCliente(
            @RequestBody @Valid RegisterRequest registerRequest
            ) throws UserException {
        Cliente novoCliente = clienteService.adicionarCliente(registerRequest);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

//    Deletar um cliente
    @DeleteMapping(path = "/{clienteId}")
    public ResponseEntity<Cliente> deleteCliente(
            @PathVariable("clienteId") Long ClienteId
    ) throws UserNotFoundException {
        clienteService.deleteCliente(ClienteId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    Atualizar um cliente
    @PutMapping(path = "/{clienteId}")
    public ResponseEntity<Cliente> atualizarCliente(
            @PathVariable("clienteId") Long clienteId,
            @RequestBody RegisterRequest registerRequest
    ) throws UserNotFoundException, UserException {
        Cliente novoCliente = clienteService.atualizarCliente(clienteId, registerRequest);
        return new ResponseEntity<>(novoCliente, HttpStatus.OK);
    }

}
