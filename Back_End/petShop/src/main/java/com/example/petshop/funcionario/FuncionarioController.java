package com.example.petshop.funcionario;

import com.example.petshop.usuarios.Usuario;
import com.example.petshop.usuarios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<Funcionario> getFuncionarios() {
        return funcionarioService.getFuncionarios();
    }

    @PostMapping
    public void registerNewUser(@RequestBody Funcionario funcionario) {
        funcionarioService.adicionarNovoFuncionario(funcionario);
    }

    @DeleteMapping(path = "{funcionarioId}")
    public void deleteFuncionario(@PathVariable("funcionarioId") Long funcionarioId) {
        funcionarioService.deleteFuncionario(funcionarioId);
    }

    @PutMapping(path = "{funcionarioId}")
    public void atualizarFuncionario(
            @PathVariable("funcionarioId") Long funcionarioId,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email
    ) {
        funcionarioService.atualizarFuncionario(funcionarioId, nome, email);
    }


}
