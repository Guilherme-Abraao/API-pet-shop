package com.example.petshop.funcionario;

import com.example.petshop.usuarios.Usuario;
import com.example.petshop.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> getFuncionarios() {

        return funcionarioRepository.findAll();
    }

    public void adicionarNovoFuncionario(Funcionario funcionario) {
        Optional<Funcionario> funcionarioOptional = funcionarioRepository.findUsuarioByEmail(funcionario.getEmail());
        if (funcionarioOptional.isPresent()) {
            throw new IllegalStateException("email já existe");
        }
        funcionarioRepository.save(funcionario);

    }
}
