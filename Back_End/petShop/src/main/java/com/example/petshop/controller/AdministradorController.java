package com.example.petshop.controller;
import com.example.petshop.base.Administrador;
import com.example.petshop.base.RegisterRequest;
import com.example.petshop.exception.UserException;
import com.example.petshop.service.AdministradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/petshop/administrador")
public class AdministradorController {

    private final AdministradorService administradorService;

    @Autowired
    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    //    Encontrar todos os funcionários
    @GetMapping
    public ResponseEntity<List<Administrador>> getAllAdministradores() {
        List<Administrador> administradores = administradorService.getAdministradores();
        return new ResponseEntity<>(administradores, HttpStatus.OK);
    }

    //    Encontrar apenas um funcionário
    @GetMapping(path = "/{id}")
    public ResponseEntity<Administrador> getAdministradorById(
            @PathVariable("id") Long id
    ) throws UserException {
        Administrador administrador = administradorService.findAdministradorById(id);
        return new ResponseEntity<>(administrador, HttpStatus.OK);
    }

    @PostMapping(path = "/cadastrarAdministrador")
    public ResponseEntity<Administrador> adicionarAdministrador(
            @RequestBody @Valid RegisterRequest registerRequest
    ) throws UserException {
        Administrador novoAdministrador = administradorService.adicionarAdministrador(registerRequest);
        return new ResponseEntity<>(novoAdministrador, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{administradorId}")
    public ResponseEntity<Administrador> deleteAdministrador(
            @PathVariable("administradorId") Long AdministradorId
    ) throws UserException {
        administradorService.deleteAdministrador(AdministradorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/{administradorId}")
    public ResponseEntity<Administrador> atualizarAdministrador(
            @PathVariable("administradorId") Long administradorId,
            @RequestBody RegisterRequest registerRequest
    ) throws UserException {
        Administrador novoAdministrador = administradorService.atualizarAdministrador(administradorId, registerRequest);
        return new ResponseEntity<>(novoAdministrador, HttpStatus.OK);
    }

}
