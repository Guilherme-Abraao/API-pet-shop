package com.example.petshop.service;

import com.example.petshop.agendamento.*;
import com.example.petshop.base.Funcionario;
import com.example.petshop.exception.AgendamentoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AgendamentoServiceTest {

    @InjectMocks
    private AgendamentoService agendamentoService;

    @Mock
    private AgendamentoRepository agendamentoRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void agendarServicos_Success() {
        List<AgendamentoRequest> requests = new ArrayList<>();


        when(agendamentoRepository.countByFuncionarioAndDataHora(any(Funcionario.class), any(LocalDateTime.class))).thenReturn(0);
        when(agendamentoRepository.saveAll(anyList())).thenReturn(new ArrayList<>());

        List<Agendamento> agendamentos = agendamentoService.agendarServicos(requests);

        assertNotNull(agendamentos);
        assertEquals(requests.size(), agendamentos.size());

        verify(agendamentoRepository, times(requests.size())).countByFuncionarioAndDataHora(any(Funcionario.class), any(LocalDateTime.class));
        verify(agendamentoRepository, times(1)).saveAll(anyList());
    }


    @Test
    void obterAgendamentoPorId_Existente() {
        Long agendamentoId = 1L;
        Agendamento agendamento = new Agendamento();

        when(agendamentoRepository.findById(agendamentoId)).thenReturn(Optional.of(agendamento));

        Agendamento result = agendamentoService.obterAgendamentoPorId(agendamentoId);

        assertNotNull(result);
        assertEquals(agendamento, result);

        verify(agendamentoRepository, times(1)).findById(agendamentoId);
    }

    @Test
    void obterAgendamentoPorId_Inexistente_Exception() {
        Long agendamentoId = 1L;

        when(agendamentoRepository.findById(agendamentoId)).thenReturn(Optional.empty());

        assertThrows(AgendamentoException.class, () -> agendamentoService.obterAgendamentoPorId(agendamentoId));

        verify(agendamentoRepository, times(1)).findById(agendamentoId);
    }

    @Test
    void deleteAgendamento_ExistingId_Success() {
        Long agendamentoId = 1L;

        when(agendamentoRepository.existsById(agendamentoId)).thenReturn(true);

        assertDoesNotThrow(() -> agendamentoService.deleteAgendamento(agendamentoId));

        verify(agendamentoRepository, times(1)).existsById(agendamentoId);
        verify(agendamentoRepository, times(1)).deleteById(agendamentoId);
    }

    @Test
    void deleteAgendamento_NonExistingId_Exception() {
        Long agendamentoId = 1L;

        when(agendamentoRepository.existsById(agendamentoId)).thenReturn(false);

        assertThrows(AgendamentoException.class, () -> agendamentoService.deleteAgendamento(agendamentoId));

        verify(agendamentoRepository, times(1)).existsById(agendamentoId);
        verify(agendamentoRepository, never()).deleteById(agendamentoId);
    }

    // Adicione mais testes para os demais métodos da classe AgendamentoService

}