package com.example.petshop.service;

import com.example.petshop.base.Administrador;
import com.example.petshop.base.RegisterRequest;
import com.example.petshop.exception.UserException;
import com.example.petshop.repository.AdministradorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AdministradorServiceTest {

    @InjectMocks
    private AdministradorService administradorService;

    @Mock
    private AdministradorRepository administradorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void adicionarAdministrador_ValidRequest_Success() throws UserException {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@example.com");
        request.setCpf("12345678901");

        when(administradorRepository.findAdministradorByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(administradorRepository.save(any(Administrador.class))).thenReturn(new Administrador());

        Administrador administrador = administradorService.adicionarAdministrador(request);

        assertNotNull(administrador);
        assertEquals(request.getEmail(), administrador.getEmail());
        assertEquals(request.getCpf(), administrador.getCpf());

        verify(administradorRepository, times(1)).findAdministradorByEmail(request.getEmail());
        verify(administradorRepository, times(1)).save(any(Administrador.class));
    }

    @Test
    void deleteAdministrador_ExistingAdministrador_Success() throws UserException {
        Long administradorId = 1L;

        when(administradorRepository.existsById(administradorId)).thenReturn(true);

        assertDoesNotThrow(() -> administradorService.deleteAdministrador(administradorId));

        verify(administradorRepository, times(1)).existsById(administradorId);
        verify(administradorRepository, times(1)).deleteById(administradorId);
    }

    @Test
    void deleteAdministrador_NonExistingAdministrador_UserException() {
        Long administradorId = 1L;

        when(administradorRepository.existsById(administradorId)).thenReturn(false);

        assertThrows(UserException.class, () -> administradorService.deleteAdministrador(administradorId));

        verify(administradorRepository, times(1)).existsById(administradorId);
        verify(administradorRepository, never()).deleteById(administradorId);
    }

    @Test
    void findAdministradorById_ExistingAdministrador_Success() throws UserException {
        Long administradorId = 1L;
        Administrador existingAdministrador = new Administrador();

        when(administradorRepository.findById(administradorId)).thenReturn(Optional.of(existingAdministrador));

        Administrador administrador = administradorService.findAdministradorById(administradorId);

        assertNotNull(administrador);
        assertEquals(existingAdministrador, administrador);

        verify(administradorRepository, times(1)).findById(administradorId);
    }

    @Test
    void findAdministradorById_NonExistingAdministrador_UserException() {
        Long administradorId = 1L;

        when(administradorRepository.findById(administradorId)).thenReturn(Optional.empty());

        assertThrows(UserException.class, () -> administradorService.findAdministradorById(administradorId));

        verify(administradorRepository, times(1)).findById(administradorId);
    }

    @Test
    void atualizarAdministrador_ValidRequest_Success() throws UserException {
        Long administradorId = 1L;
        RegisterRequest request = new RegisterRequest();
        request.setNome("Novo Nome");
        request.setEmail("novo.email@example.com");
        request.setCpf("12345678901");

        Administrador existingAdministrador = new Administrador();
        existingAdministrador.setId(administradorId);
        existingAdministrador.setEmail("antigo.email@example.com");
        existingAdministrador.setCpf("98765432101");

        when(administradorRepository.findById(administradorId)).thenReturn(Optional.of(existingAdministrador));
        when(administradorRepository.findAdministradorByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(administradorRepository.findAdministradorByCpf(request.getCpf())).thenReturn(Optional.empty());
        when(administradorRepository.save(any(Administrador.class))).thenReturn(existingAdministrador);

        Administrador updatedAdministrador = administradorService.atualizarAdministrador(administradorId, request);

        assertNotNull(updatedAdministrador);
        assertEquals(request.getNome(), updatedAdministrador.getNome());
        assertEquals(request.getEmail(), updatedAdministrador.getEmail());
        assertEquals(request.getCpf(), updatedAdministrador.getCpf());

        verify(administradorRepository, times(1)).findById(administradorId);
        verify(administradorRepository, times(1)).findAdministradorByEmail(request.getEmail());
        verify(administradorRepository, times(1)).findAdministradorByCpf(request.getCpf());
        verify(administradorRepository, times(1)).save(any(Administrador.class));
    }


}