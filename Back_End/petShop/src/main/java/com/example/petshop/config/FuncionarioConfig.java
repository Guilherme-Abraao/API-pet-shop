/* package com.example.petshop.config;

import com.example.petshop.base.Funcionario;
import com.example.petshop.repository.FuncionarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class FuncionarioConfig {

    @Bean
    CommandLineRunner commandLineRunner (FuncionarioRepository funcionarioRepository) {
        return args -> {
            Funcionario tony = new Funcionario(
                    "Tony",
                    "tony.stark@gmail.com",
                    "55693710017",
                    "(62) 28362-4799",
                    "8r0A8nTjHx",
                    LocalDate.of(1980, Month.MARCH, 17),
                    "gerente",
                    10000.00
            );

            Funcionario steve = new Funcionario(
                    "Steve",
                    "steve.rodgers@gmail.com",
                    "60802561004",
                    "(62) 44376-6702",
                    "1iInHMJaTd",
                    LocalDate.of(1997, Month.APRIL, 23),
                    "auxiliar",
                    5000.00
            );

            Funcionario thor = new Funcionario(
                    "Thor",
                    "thor.odinson@gmail.com",
                    "69613325018",
                    "(62) 55191-2878",
                    "a3gurD8ciF",
                    LocalDate.of(2005, Month.MAY, 05),
                    "porteiro",
                    5000.00
            );
            funcionarioRepository.saveAll(
                    List.of(tony, steve, thor)
            );
        };
    }
} */
