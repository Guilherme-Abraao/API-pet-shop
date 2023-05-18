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


}
