package com.example.petshop.repository;


import com.example.petshop.base.Cliente;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

@DataJpaTest
class ClienteRepositoryTest {

    @Autowired
    private ClienteRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void TestFindClienteByEmail() {

        String email = "barry.allen@gmail.com";

        Cliente cliente = new Cliente(
                "Barry",
                "barry.allen@gmail.com",
                "14180500086",
                "(62) 22097-8318",
                "PuZfPjDQo6",
                LocalDate.of(1995, Month.JULY, 14)
        );

        underTest.save(cliente);

        //when
        Optional<Cliente> expected = underTest.findClienteByEmail(email);



        //then
        Assertions.assertThat(expected).isPresent();

    }
}