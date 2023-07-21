//package com.example.petshop.repository;
//
//
//import org.assertj.core.api.Assertions;
//import com.example.petshop.base.Animal;
//import com.example.petshop.base.Cliente;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.Optional;
//
//import static com.example.petshop.base.Role.CLIENTE;
//
//
//@DataJpaTest
//public class AnimalRepositoryTest {
//
//    @Autowired
//    private  AnimalRepository underTest;
//    private ClienteRepository underTestC;
//
//    @AfterEach
//
//    void teardown() {underTest.deleteAll();}
//
//    @Test
//
//    void TestfindAnimalByName(){
//
//
//        String nome = "Soneca";
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
//        Animal soneca = new Animal(
//                "Soneca",
//                9,
//                billy
//        );
//
//        underTest.save(soneca);
//
//        //when
//
//        Optional<Cliente> expected = underTest.findAnimalByName(nome);
//
//        //then
//
//        Assertions.assertThat(expected).isPresent();
//
//
//
//
//
//    }
//
//
//}
