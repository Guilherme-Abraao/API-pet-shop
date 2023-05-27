package com.example.petshop.controller;

import com.example.petshop.base.Funcionario;
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
    @GetMapping(path = "/find/{id}")
    public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable("id") Long id) {
        Funcionario Funcionario = funcionarioService.findFuncionarioById(id);
        return new ResponseEntity<>(Funcionario, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Funcionario> adicionarFuncionario(@RequestBody @Valid Funcionario funcionario) {
        Funcionario novoFuncionario = funcionarioService.adicionarFuncionario(funcionario);
        return new ResponseEntity<>(novoFuncionario, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{funcionarioId}")
    public ResponseEntity<Funcionario> deleteFuncionario(@PathVariable("funcionarioId") Long FuncionarioId) {
        funcionarioService.deleteFuncionario(FuncionarioId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "{funcionarioId}")
    public ResponseEntity<Funcionario> atualizarFuncionario(
            @PathVariable("funcionarioId") Long funcionarioId,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email
    ) {
        Funcionario novoFuncionario = funcionarioService.atualizarFuncionario(funcionarioId, nome, email);
        return new ResponseEntity<>(novoFuncionario, HttpStatus.OK);
    }

}
