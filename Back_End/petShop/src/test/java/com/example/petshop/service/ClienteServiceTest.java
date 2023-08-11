package com.example.petshop.service;

import com.example.petshop.base.Cliente;
import com.example.petshop.base.RegisterRequest;
import com.example.petshop.exception.UserException;
import com.example.petshop.repository.ClienteRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.assertj.core.api.Assertions;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {


    @Mock
    private ClienteRepository clienteRepository;

    private ClienteService underTest;

    @BeforeEach
    void setUp() {

        underTest = new ClienteService(clienteRepository);
    }



    @Test

    void getClientes() {

        //when
        underTest.getClientes();

        //then
        verify(clienteRepository).findAll();
    }


    @Test

    void ConssegueadicionarCliente() throws UserException {


        //given
        Cliente cliente = new Cliente(
                "Billy",
                "billy.batson@gmail.com",
                "38060025090",
                "(62) 39020-1931",
                "iihzNM37gF",
                LocalDate.of(1998, Month.MARCH, 24)
        );

        RegisterRequest registerCliente = new RegisterRequest();
        registerCliente.setNome(cliente.getNome());
        registerCliente.setEmail(cliente.getEmail());
        registerCliente.setCpf(cliente.getCpf());
        registerCliente.setTelefone(cliente.getTelefone());
        registerCliente.setSenha(cliente.getSenha());
        registerCliente.setDataNascimento(cliente.getDataNascimento());

        //when
        underTest.adicionarCliente(registerCliente);

        //then
        ArgumentCaptor<Cliente> clienteArgumentCaptor = ArgumentCaptor.forClass(Cliente.class);

        verify(clienteRepository).save(clienteArgumentCaptor.capture());

        Cliente capturedCliente = clienteArgumentCaptor.getValue();

        Assertions.assertThat(capturedCliente).isEqualTo(cliente);
    }

    @Test
    @Disabled
    void deleteCliente() {
    }

    @Test
    @Disabled
    void findClienteById() {
    }

    @Test
    @Disabled
    void atualizarCliente() {
    }
}