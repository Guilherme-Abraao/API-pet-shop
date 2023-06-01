package com.example.petshop.service;


import com.example.petshop.base.Funcionario;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.petshop.repository.FuncionarioRepository;
import org.assertj.core.api.Assertions;
import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FuncionarioServiceTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    private FuncionarioService underTest;

    @BeforeEach
    void setUp(){
        underTest = new FuncionarioService(funcionarioRepository);
    }

    @Test
    void getFuncionarios() {
        //when
        underTest.getFuncionarios();

        //then

        verify(funcionarioRepository).findAll();
    }

    @Test
    void ConssegueadicionarFuncionario() {

        //given
        Funcionario funcionario = new Funcionario(
                "Tony",
                "tony.stark@gmail.com",
                "55693710017",
                "(62) 28362-4799",
                "8r0A8nTjHx",
                LocalDate.of(1980, Month.MARCH, 17),
                "gerente",
                10000.00
        );

        //when
        underTest.adicionarFuncionario(funcionario);

        //then

        ArgumentCaptor<Funcionario> funcionarioArgumentCaptor = ArgumentCaptor.forClass(Funcionario.class);

        verify(funcionarioRepository).save(funcionarioArgumentCaptor.capture());

        Funcionario capturedFuncionario = funcionarioArgumentCaptor.getValue();

        Assertions.assertThat(capturedFuncionario).isEqualTo(funcionario);





    }
    @Disabled
    @Test
    void deleteFuncionario() {
    }
    @Disabled
    @Test
    void findFuncionarioById() {
    }
    @Disabled
    @Test
    void atualizarFuncionario() {
    }
}