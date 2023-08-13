package com.example.petshop.service;

import com.example.petshop.base.Animal;
import com.example.petshop.base.AnimalRegisterRequest;
import com.example.petshop.base.Cliente;
import com.example.petshop.exception.UserException;
import com.example.petshop.repository.AnimalRepository;
import com.example.petshop.repository.ClienteRepository;
import com.example.petshop.service.AnimalService;
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
import static org.mockito.Mockito.*;

class AnimalServiceTest{

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalService animalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAnimais_Funciona() {
        List<Animal> mockAnimais = new ArrayList<>();
        when(animalRepository.findAll()).thenReturn(mockAnimais);

        List<Animal> animais = animalService.getAnimais();

        assertEquals(mockAnimais, animais);
    }

    @Test
    void findAnimalById_Id_existe() throws UserException {
        Long animalId = 1L;
        Animal mockAnimal = new Animal();
        when(animalRepository.findById(animalId)).thenReturn(Optional.of(mockAnimal));

        Animal animal = animalService.findAnimalById(animalId);

        assertEquals(mockAnimal, animal);
    }

    @Test
    void findAnimalById_Id_nao_existe_ThrowsUserException() {
        Long nonExistingId = 100L;
        when(animalRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(UserException.class, () -> animalService.findAnimalById(nonExistingId));
    }

    @Test
    void cadastrarAnimal_ValidRequestAndExistingCliente_Success() throws UserException {
        Long clienteId = 1L;
        AnimalRegisterRequest request = new AnimalRegisterRequest();
        Cliente cliente = new Cliente();
        request.setCliente(cliente);

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));

        when(animalRepository.save(any(Animal.class))).thenReturn(new Animal());

        Animal animal = animalService.cadastrarAnimal(request, clienteId);

        assertNotNull(animal);


        verify(clienteRepository, times(1)).findById(clienteId);
        verify(animalRepository, times(1)).save(any(Animal.class));
    }

    @Test
    void cadastrarAnimal_Cliente_Nao_Existe_ThrowsUserException() {
        Long nonExistingClienteId = 100L;
        AnimalRegisterRequest request = new AnimalRegisterRequest();

        when(clienteRepository.findById(nonExistingClienteId)).thenReturn(Optional.empty());

        assertThrows(UserException.class, () -> animalService.cadastrarAnimal(request, nonExistingClienteId));
    }

    @Test
    void deletarAnimal_Animal_Existe_Success() {
        Long animalId = 1L;
        when(animalRepository.existsById(animalId)).thenReturn(true);

        assertDoesNotThrow(() -> animalService.deletarAnimal(animalId));
        verify(animalRepository, times(1)).deleteById(animalId);
    }

    @Test
    void deletarAnimal_Animal_Nao_Existe_ThrowsIllegalStateException() {
        Long nonExistingAnimalId = 100L;
        when(animalRepository.existsById(nonExistingAnimalId)).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> animalService.deletarAnimal(nonExistingAnimalId));
    }

    @Test
    void atualizarAnimal_ExistingAnimal_Success() {
        Long animalId = 1L;
        Long clienteId = 1L;
        AnimalRegisterRequest request = new AnimalRegisterRequest();
        Animal existingAnimal = new Animal();
        Cliente cliente = new Cliente();
        request.setCliente(cliente);

        when(animalRepository.findById(animalId)).thenReturn(Optional.of(existingAnimal));
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));
        when(animalRepository.save(any(Animal.class))).thenReturn(existingAnimal);

        Animal updatedAnimal = animalService.atualizarAnimal(animalId, clienteId, request);

        assertNotNull(updatedAnimal);
        assertEquals(existingAnimal, updatedAnimal);

        verify(animalRepository, times(1)).findById(animalId);
        verify(clienteRepository, times(1)).findById(clienteId);
        verify(animalRepository, times(1)).save(any(Animal.class));
    }

    @Test
    void atualizarAnimal_NonExistingAnimal_ThrowsIllegalStateException() {
        Long nonExistingAnimalId = 100L;
        Long clienteId = 1L;
        AnimalRegisterRequest request = new AnimalRegisterRequest();

        when(animalRepository.findById(nonExistingAnimalId)).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, () -> animalService.atualizarAnimal(nonExistingAnimalId, clienteId, request));
    }

}