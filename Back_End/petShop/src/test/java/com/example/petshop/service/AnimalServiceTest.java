//package com.example.petshop.service;
//
//import com.example.petshop.base.Animal;
//import com.example.petshop.base.Cliente;
//import com.example.petshop.exception.UserNotFoundException;
//import com.example.petshop.repository.AnimalRepository;
//import com.example.petshop.repository.ClienteRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//import java.time.Month;
//
//import static com.example.petshop.base.Role.CLIENTE;
//import static org.junit.jupiter.api.Assertions.*;
//
//import static org.mockito.Mockito.verify;
//
//
//@ExtendWith(MockitoExtension.class)
//
//class AnimalServiceTest {
//
//    @Mock
//    private ClienteRepository clienteRepository;
//
//    private AnimalRepository animalRepository;
//
//    private AnimalService underTestAnimal;
//
//    private ClienteService underTestClient;
//
//    @BeforeEach
//
//    void seUp(){
//        underTestAnimal = new AnimalService(clienteRepository,animalRepository);
//    }
//
//
//    @Test
//    void getAnimais() {
//
//        //when
//        underTestAnimal.getAnimais();
//
//        //then
//        verify(animalRepository).findAll();
//    }
//
//    @Test
//    @Disabled
//    void findAnimalById() {
//
//    }
//
//    @Test
//
//    void cadastrarAnimal() throws UserNotFoundException {
//        //given
//
//        Cliente billy = new Cliente(
//                "Billy",
//                "billy.batson@gmail.com",
//                "38060025090",
//                "(62) 39020-1931",
//                "iihzNM37gF",
//                LocalDate.of(1998, Month.MARCH, 24),
//                CLIENTE
//        );
//
//        Animal soneca = new Animal(
//                "Soneca",
//                9,
//                billy
//        );
//
//        //when
//
//        underTestClient.adicionarCliente(billy);
//        underTestAnimal.cadastrarAnimal(soneca,billy.getId());
//
//        //then
//
//        ArgumentCaptor<Cliente> clienteArgumentCaptor = ArgumentCaptor.forClass(Cliente.class);
//
//        verify(clienteRepository).save(clienteArgumentCaptor.capture());
//
//        Cliente capturedCliente = clienteArgumentCaptor.getValue();
//
//        Assertions.assertThat(capturedCliente.getAnimais()).isEqualTo(soneca);
//
//
//
//
//
//
//
//    }
//
//    @Test
//    @Disabled
//    void deleteAnimal() {
//    }
//
//    @Test
//    @Disabled
//    void atualizarAnimal() {
//    }
//}