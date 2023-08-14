package com.example.petshop.service;

import com.example.petshop.base.Animal;
import com.example.petshop.base.AnimalRegisterRequest;
import com.example.petshop.base.Cliente;
import com.example.petshop.base.RegisterRequest;
import com.example.petshop.exception.UserException;
import com.example.petshop.repository.AnimalRepository;
import com.example.petshop.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;

import static java.time.LocalDate.of;
import static java.time.Month.APRIL;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)

class AnimalServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    private ClienteService underTestClient;

    private AnimalRepository animalRepository;

    private AnimalService underTestAnimal;



    @BeforeEach

    void seUp(){
//        underTestClient = new ClienteService(clienteRepository);
        underTestAnimal = new AnimalService(clienteRepository,animalRepository);

    }


    @Test
    void getAnimais() {

        //when
        underTestAnimal.getAnimais();

        //then
        verify(animalRepository).findAll();
    }

    @Test
    @Disabled
    void findAnimalById() {

    }

    @Test

    void cadastrarAnimal() throws UserException {
        //given

        Cliente billy = new Cliente(
                "Billy",
                "billy.batson@gmail.com",
                "38060025090",
                "(62) 39020-1931",
                "iihzNM37gF",
                LocalDate.of(1998, Month.MARCH, 24)
        );

        RegisterRequest registerBilly = new RegisterRequest();
        registerBilly.setNome(billy.getNome());
        registerBilly.setEmail(billy.getEmail());
        registerBilly.setCpf(billy.getCpf());
        registerBilly.setTelefone(billy.getTelefone());
        registerBilly.setSenha(billy.getSenha());
        registerBilly.setDataNascimento(billy.getDataNascimento());

        Animal soneca = new Animal(
                "Soneca",
                of(2010, APRIL, 15),
                "Cachorro",
                "American Bully",
                billy
        );

        AnimalRegisterRequest registerSoneca = new AnimalRegisterRequest();
        registerSoneca.setNome(soneca.getNome());
        registerSoneca.setDataNascimento(soneca.getDataNascimento());
        registerSoneca.setEspecie(soneca.getEspecie());
        registerSoneca.setRaca(soneca.getRaca());
        registerSoneca.setCliente(soneca.getCliente());

        //when

        underTestClient.adicionarCliente(registerBilly);
        underTestAnimal.cadastrarAnimal(registerSoneca, billy.getId());

        //then








    }

    @Test
    @Disabled
    void deleteAnimal() {
    }

    @Test
    @Disabled
    void atualizarAnimal() {
    }


}