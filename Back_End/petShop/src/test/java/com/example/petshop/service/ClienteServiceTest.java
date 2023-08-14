package com.example.petshop.service;

import com.example.petshop.base.Cargo;
import com.example.petshop.base.Cliente;
import com.example.petshop.base.RegisterRequest;
import com.example.petshop.exception.UserException;
import com.example.petshop.repository.ClienteRepository;
import com.example.petshop.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getClientes_ReturnsListOfClientes() {
        List<Cliente> expectedClientes = new ArrayList<>();
        expectedClientes.add(new Cliente());
        expectedClientes.add(new Cliente());

        when(clienteRepository.findAll()).thenReturn(expectedClientes);

        List<Cliente> actualClientes = clienteService.getClientes();

        assertEquals(expectedClientes.size(), actualClientes.size());
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void findClienteById_Cliente_Existe_ReturnsCliente() throws UserException {
        Long clienteId = 1L;
        Cliente expectedCliente = new Cliente();

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(expectedCliente));

        Cliente actualCliente = clienteService.findClienteById(clienteId);

        assertEquals(expectedCliente, actualCliente);
        verify(clienteRepository, times(1)).findById(clienteId);
    }

    @Test
    void findClienteById_Cliente_Inexistente_ThrowsUserException() {
        Long clienteId = 1L;

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.empty());

        assertThrows(UserException.class, () -> clienteService.findClienteById(clienteId));
        verify(clienteRepository, times(1)).findById(clienteId);
    }

    @Test
    void adicionarCliente_ValidRequest_Success() throws UserException {

        RegisterRequest request = createSampleRegisterRequest();

        when(clienteRepository.findClienteByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(clienteRepository.findClienteByCpf(request.getCpf())).thenReturn(Optional.empty());
        when(clienteRepository.save(any(Cliente.class))).thenReturn(new Cliente());

        Cliente cliente = clienteService.adicionarCliente(request);

        assertNotNull(cliente);

        verify(clienteRepository, times(1)).findClienteByEmail(request.getEmail());
        verify(clienteRepository, times(1)).findClienteByCpf(request.getCpf());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void adicionarCliente_DuplicateEmail_ThrowsUserException() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@example.com");

        when(clienteRepository.findClienteByEmail(request.getEmail())).thenReturn(Optional.of(new Cliente()));

        assertThrows(UserException.class, () -> clienteService.adicionarCliente(request));
        verify(clienteRepository, times(1)).findClienteByEmail(request.getEmail());
        verify(clienteRepository, never()).findClienteByCpf(anyString());
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    void adicionarCliente_DuplicateCpf_ThrowsUserException() {
        RegisterRequest request = new RegisterRequest();
        request.setCpf("12345678901");

        when(clienteRepository.findClienteByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(clienteRepository.findClienteByCpf(request.getCpf())).thenReturn(Optional.of(new Cliente()));

        assertThrows(UserException.class, () -> clienteService.adicionarCliente(request));
        verify(clienteRepository, times(1)).findClienteByEmail(request.getEmail());
        verify(clienteRepository, times(1)).findClienteByCpf(request.getCpf());
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    void login_ValidCredentials_ReturnsCliente() throws UserException {
        String email = "test@example.com";
        String senha = "password";
        Cliente expectedCliente = new Cliente();
        expectedCliente.setSenha(senha);

        when(clienteRepository.findClienteByEmail(email)).thenReturn(Optional.of(expectedCliente));

        Cliente actualCliente = clienteService.login(email, senha);

        assertEquals(expectedCliente, actualCliente);
        verify(clienteRepository, times(1)).findClienteByEmail(email);
    }

    @Test
    void login_InvalidEmail_ThrowsUserException() {
        String email = "test@example.com";
        String senha = "password";

        when(clienteRepository.findClienteByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UserException.class, () -> clienteService.login(email, senha));
        verify(clienteRepository, times(1)).findClienteByEmail(email);
    }


    @Test
    void login_IncorrectPassword_ThrowsUserException() {
        String email = "test@example.com";
        String senha = "password";
        Cliente cliente = new Cliente();
        cliente.setSenha("wrongpassword");

        RegisterRequest registerCliente = new RegisterRequest();
        registerCliente.setNome(cliente.getNome());
        registerCliente.setEmail(cliente.getEmail());
        registerCliente.setCpf(cliente.getCpf());
        registerCliente.setTelefone(cliente.getTelefone());
        registerCliente.setSenha(cliente.getSenha());
        registerCliente.setDataNascimento(cliente.getDataNascimento());


        when(clienteRepository.findClienteByEmail(email)).thenReturn(Optional.of(cliente));

        assertThrows(UserException.class, () -> clienteService.login(email, senha));
        verify(clienteRepository, times(1)).findClienteByEmail(email);
    }

    @Test
    void deleteCliente_ExistingCliente_Success() throws UserException {
        Long clienteId = 1L;

        when(clienteRepository.existsById(clienteId)).thenReturn(true);

        assertDoesNotThrow(() -> clienteService.deleteCliente(clienteId));
        verify(clienteRepository, times(1)).existsById(clienteId);
        verify(clienteRepository, times(1)).deleteById(clienteId);
    }

    @Test
    void deleteCliente_NonExistingCliente_ThrowsUserException() {
        Long clienteId = 1L;

        when(clienteRepository.existsById(clienteId)).thenReturn(false);

        assertThrows(UserException.class, () -> clienteService.deleteCliente(clienteId));
        verify(clienteRepository, times(1)).existsById(clienteId);
        verify(clienteRepository, never()).deleteById(clienteId);
    }

    @Test
    void atualizarCliente_ValidRequest_Success() throws UserException {
        Long clienteId = 1L;
        RegisterRequest request = new RegisterRequest();
        request.setEmail("new@example.com");
        request.setCpf("98765432101");
        Cliente existingCliente = new Cliente();
        existingCliente.setId(clienteId);

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(existingCliente));
        when(clienteRepository.findClienteByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(clienteRepository.findClienteByCpf(request.getCpf())).thenReturn(Optional.empty());
        when(clienteRepository.save(any(Cliente.class))).thenReturn(existingCliente);

        Cliente updatedCliente = clienteService.atualizarCliente(clienteId, request);

        assertNotNull(updatedCliente);
        assertEquals(request.getEmail(), updatedCliente.getEmail());
        assertEquals(request.getCpf(), updatedCliente.getCpf());
        verify(clienteRepository, times(1)).findById(clienteId);
        verify(clienteRepository, times(1)).findClienteByEmail(request.getEmail());
        verify(clienteRepository, times(1)).findClienteByCpf(request.getCpf());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void atualizarCliente_NonExistingCliente_ThrowsUserException() {
        Long clienteId = 1L;
        RegisterRequest request = new RegisterRequest();
        request.setEmail("new@example.com");

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.empty());

        assertThrows(UserException.class, () -> clienteService.atualizarCliente(clienteId, request));
        verify(clienteRepository, times(1)).findById(clienteId);
        verify(clienteRepository, never()).findClienteByEmail(anyString());
        verify(clienteRepository, never()).findClienteByCpf(anyString());
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    private RegisterRequest createSampleRegisterRequest() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@example.com");
        request.setCpf("12345678901");
        request.setNome("John Doe");
        request.setTelefone("1234567890");
        request.setSenha("password");
        request.setDataNascimento(LocalDate.of(1990, 1, 1));

        return request;
    }
}