//package com.example.petshop.repository;
//
//import com.example.petshop.base.Funcionario;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DataJpaTest
//class AdministradorRepositoryTest {
//
//    @Autowired
//    private FuncionarioRepository underTest;
//
//    @AfterEach
//    void tearDown() {
//        underTest.deleteAll();
//    }
//
//    @Test
//    void findAdministradorByEmail() {
//        String email = "tony.stark@gmail.com";
//
//        Funcionario funcionario = new Funcionario(
//                "Tony",
//                "tony.stark@gmail.com",
//                "55693710017",
//                "(62) 28362-4799",
//                "8r0A8nTjHx",
//                LocalDate.of(1980, Month.MARCH, 17),
//                "gerente",
//                10000.00
//        );
//
//        underTest.save(funcionario);
//
//        //when
//        Optional<Funcionario> expected = underTest.findFuncionarioByEmail(email);
//
//
//
//        //then
//        Assertions.assertThat(expected).isPresent();
//
//    }
//}