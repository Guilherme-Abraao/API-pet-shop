package com.example.petshop.controller;
import com.example.petshop.base.Administrador;
import com.example.petshop.service.AdministradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/petshop/administrador")
@PreAuthorize("hasRole('ADMIN')")
public class AdministradorController {

    private final AdministradorService administradorService;

    @Autowired
    public AdministradorController(AdministradorService administradorService) {
        this.administradorService = administradorService;
    }

    //    Encontrar todos os funcionários
    @GetMapping
    @PreAuthorize("hasRole('admin:read')")
    public ResponseEntity<List<Administrador>> getAllAdministradors() {
        List<Administrador> Administradors = administradorService.getAdministradores();
        return new ResponseEntity<>(Administradors, HttpStatus.OK);
    }

    //    Encontrar apenas um funcionário
    @GetMapping(path = "/find/{id}")
    @PreAuthorize("hasRole('admin:read')")
    public ResponseEntity<Administrador> getAdministradorById(@PathVariable("id") Long id) {
        Administrador Administrador = administradorService.findAdministradorById(id);
        return new ResponseEntity<>(Administrador, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('admin:create')")
    public ResponseEntity<Administrador> adicionarAdministrador(@RequestBody @Valid Administrador administrador) {
        Administrador novoAdministrador = administradorService.adicionarAdministrador(administrador);
        return new ResponseEntity<>(novoAdministrador, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "{administradorId}")
    @PreAuthorize("hasRole('admin:delete')")
    public ResponseEntity<Administrador> deleteAdministrador(@PathVariable("administradorId") Long AdministradorId) {
        administradorService.deleteAdministrador(AdministradorId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "{administradorId}")
    @PreAuthorize("hasRole('admin:update')")
    public ResponseEntity<Administrador> atualizarAdministrador(
            @PathVariable("administradorId") Long administradorId,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email
    ) {
        Administrador novoAdministrador = administradorService.atualizarAdministrador(administradorId, nome, email);
        return new ResponseEntity<>(novoAdministrador, HttpStatus.OK);
    }

}
