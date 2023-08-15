package com.example.petshop.controller;

import com.example.petshop.base.Cliente;
import com.example.petshop.base.EmployeeRequest;
import com.example.petshop.base.Funcionario;
import com.example.petshop.exception.UserException;
import com.example.petshop.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "api/petshop/funcionario")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    //    Encontrar todos os funcionários
    @GetMapping
    public ResponseEntity<List<Funcionario>> getAllFuncionarios() {
        List<Funcionario> Funcionarios = funcionarioService.getFuncionarios();
        return new ResponseEntity<>(Funcionarios, HttpStatus.OK);
    }

    //    Encontrar apenas um funcionário
    @GetMapping(path = "/{id}")
    public ResponseEntity<Funcionario> getFuncionarioById(
            @PathVariable("id") Long id
    ) throws UserException {
        Funcionario Funcionario = funcionarioService.findFuncionarioById(id);
        return new ResponseEntity<>(Funcionario, HttpStatus.OK);
    }

    //    Login
    @GetMapping(path = "/{email}/{senha}")
    public ResponseEntity<Funcionario> login(
            @PathVariable("email") String email,
            @PathVariable("senha") String senha
    ) throws UserException {
        Funcionario funcionario = funcionarioService.login(email, senha);
        return new ResponseEntity<>(funcionario, HttpStatus.OK);
    }

    @PostMapping(path = "/cadastrarFuncionario")
    public ResponseEntity<Funcionario> adicionarFuncionario(
            @RequestBody @Valid EmployeeRequest registerRequest
            ) throws UserException {
        Funcionario novoFuncionario = funcionarioService.adicionarFuncionario(registerRequest);
        return new ResponseEntity<>(novoFuncionario, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{funcionarioId}")
    public ResponseEntity<Funcionario> deleteFuncionario(
            @PathVariable("funcionarioId") Long FuncionarioId
    ) throws UserException {
        funcionarioService.deleteFuncionario(FuncionarioId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/{funcionarioId}")
    public ResponseEntity<Funcionario> atualizarFuncionario(
            @PathVariable("funcionarioId") Long funcionarioId,
            @RequestBody EmployeeRequest registerRequest
    ) throws UserException {
        Funcionario novoFuncionario = funcionarioService.atualizarFuncionario(funcionarioId, registerRequest);
        return new ResponseEntity<>(novoFuncionario, HttpStatus.OK);
    }

}
