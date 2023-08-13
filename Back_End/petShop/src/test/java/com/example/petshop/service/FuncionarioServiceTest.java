package com.example.petshop.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.petshop.base.Cargo;
import com.example.petshop.base.Funcionario;
import com.example.petshop.base.RegisterRequest;
import com.example.petshop.exception.UserException;
import com.example.petshop.repository.FuncionarioRepository;
import com.example.petshop.service.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

public class FuncionarioServiceTest {

    private FuncionarioService funcionarioService;
    private FuncionarioRepository funcionarioRepository;

    @BeforeEach
    void setUp() {
        funcionarioRepository = mock(FuncionarioRepository.class);
        funcionarioService = new FuncionarioService(funcionarioRepository);
    }

    @Test
    void adicionarFuncionario_ValidRequest_Success() throws UserException {
        RegisterRequest request = createSampleRegisterRequest();

        when(funcionarioRepository.findFuncionarioByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(new Funcionario());

        Funcionario funcionario = funcionarioService.adicionarFuncionario(request);

        assertNotNull(funcionario);

        verify(funcionarioRepository, times(1)).findFuncionarioByEmail(request.getEmail());
        verify(funcionarioRepository, times(1)).save(any(Funcionario.class));
    }

    @Test
    void adicionarFuncionario_Email_Duplicado_ThrowsUserException() {
        RegisterRequest request = createSampleRegisterRequest();

        when(funcionarioRepository.findFuncionarioByEmail(request.getEmail())).thenReturn(Optional.of(new Funcionario()));

        assertThrows(UserException.class, () -> funcionarioService.adicionarFuncionario(request));
    }

    @Test
    void deleteFuncionario_Funcionario_Existente_Success() throws UserException {
        Long funcionarioId = 1L;

        when(funcionarioRepository.existsById(funcionarioId)).thenReturn(true);

        assertDoesNotThrow(() -> funcionarioService.deleteFuncionario(funcionarioId));

        verify(funcionarioRepository, times(1)).existsById(funcionarioId);
        verify(funcionarioRepository, times(1)).deleteById(funcionarioId);
    }

    @Test
    void deleteFuncionario_Funcionario_Inexistente_ThrowsUserException() {
        Long funcionarioId = 1L;

        when(funcionarioRepository.existsById(funcionarioId)).thenReturn(false);

        assertThrows(UserException.class, () -> funcionarioService.deleteFuncionario(funcionarioId));
    }

    @Test
    void findFuncionarioById_Funcionario_Existente_Success() throws UserException {
        Long funcionarioId = 1L;
        Funcionario funcionario = new Funcionario();

        when(funcionarioRepository.findById(funcionarioId)).thenReturn(Optional.of(funcionario));

        Funcionario foundFuncionario = funcionarioService.findFuncionarioById(funcionarioId);

        assertNotNull(foundFuncionario);
        assertEquals(funcionario, foundFuncionario);
    }

    @Test
    void findFuncionarioById_Funcionario_Inexistente_ThrowsUserException() {
        Long funcionarioId = 1L;

        when(funcionarioRepository.findById(funcionarioId)).thenReturn(Optional.empty());

        assertThrows(UserException.class, () -> funcionarioService.findFuncionarioById(funcionarioId));
    }

    @Test
    void atualizarFuncionario_ValidRequest_Success() throws UserException {
        Long funcionarioId = 1L;
        RegisterRequest request = new RegisterRequest();
        request.setNome("Novo Nome");
        request.setEmail("novo.email@example.com");
        request.setCpf("12345678901");

        Funcionario existingFuncionario = new Funcionario();
        existingFuncionario.setId(funcionarioId);
        existingFuncionario.setEmail("antigo.email@example.com");
        existingFuncionario.setCpf("98765432101");

        when(funcionarioRepository.findById(funcionarioId)).thenReturn(Optional.of(existingFuncionario));
        when(funcionarioRepository.findFuncionarioByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(funcionarioRepository.findFuncionarioByCpf(request.getCpf())).thenReturn(Optional.empty());
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(existingFuncionario);

        Funcionario updatedFuncionario = funcionarioService.atualizarFuncionario(funcionarioId, request);

        assertNotNull(updatedFuncionario);
        assertEquals(request.getNome(), updatedFuncionario.getNome());
        assertEquals(request.getEmail(), updatedFuncionario.getEmail());
        assertEquals(request.getCpf(), updatedFuncionario.getCpf());

        verify(funcionarioRepository, times(1)).findById(funcionarioId);
        verify(funcionarioRepository, times(1)).findFuncionarioByEmail(request.getEmail());
        verify(funcionarioRepository, times(1)).findFuncionarioByCpf(request.getCpf());
        verify(funcionarioRepository, times(1)).save(any(Funcionario.class));
    }


    private RegisterRequest createSampleRegisterRequest() {
        RegisterRequest request = new RegisterRequest();
        request.setNome("John Doe");
        request.setEmail("johndoe@example.com");
        request.setCpf("12345678900");
        request.setTelefone("1234567890");
        request.setSenha("password");
        request.setDataNascimento(LocalDate.of(1990, 1, 1));
        request.setCargo(Cargo.tosador);
        request.setSalario(2600.0);
        return request;
    }
}