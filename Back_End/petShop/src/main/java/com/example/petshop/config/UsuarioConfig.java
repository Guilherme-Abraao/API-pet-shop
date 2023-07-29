package com.example.petshop.config;

import com.example.petshop.agendamento.AgendamentoRepository;
import com.example.petshop.agendamento.AgendamentoRequest;
import com.example.petshop.agendamento.AgendamentoService;
import com.example.petshop.base.*;
import com.example.petshop.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static com.example.petshop.base.Role.*;
import static java.time.LocalDate.*;
import static java.time.Month.*;

@Configuration
public class UsuarioConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Bean
    CommandLineRunner commandLineRunner(
            ClienteRepository clienteRepository,
            FuncionarioRepository funcionarioRepository,
            AdministradorRepository administradorRepository,
            AnimalRepository animalRepository,
            AgendamentoService agendamentoService
    ) {
        return args -> {
            Cliente bruce = new Cliente(
                    "Bruce",
                    "bruce.wayne@gmail.com",
                    "71561133051",
                    "(11) 42852-9122",
                    "1l5O0mb4AN",
                    of(2000, JANUARY, 25),
                    CLIENTE
            );

            Cliente hector = new Cliente(
                    "Hector",
                    "hector@gmail.com",
                    "08174858130",
                    "(11) 42852-9122",
                    "1l5O0mb4AN",
                    of(2000, JANUARY, 25),
                    CLIENTE
            );

            Animal fumaca = new Animal(
                    "Fumaça",
                    of(2013, OCTOBER, 10),
                    "Akita",
                    hector
            );
            AgendamentoRequest agendamentoRequest = new AgendamentoRequest(
                    hector, // Cliente
                    fumaca, // Animal
                    LocalDateTime.of(2023, JUNE, 10, 14, 30), // Data e hora do agendamento
                    Servico.BANHO // Serviço a ser realizado
            );

            Cliente aquiles = new Cliente(
                    "Aquiles",
                    "aquiles@gmail.com",
                    "70494074108",
                    "(11) 42852-9122",
                    "1l5O0mb4AN",
                    of(2000, JANUARY, 25),
                    CLIENTE
            );

            Animal floquinho = new Animal(
                    "Floquinho",
                    of(2015, FEBRUARY, 13),
                    "Basset hound",
                    bruce
            );

            Funcionario barry = new Funcionario(
                    "Barry",
                    "barry.allen@gmail.com",
                    "14180500086",
                    "(62) 22097-8318",
                    "PuZfPjDQo6",
                    of(1995, JULY, 14),
                    FUNCIONARIO,
                    "recepcionista",
                    1045.65
            );

            Animal luke = new Animal(
                    "Luke",
                    of(2014, NOVEMBER, 5),
                    "Basenji",
                    bruce
            );

            Cliente billy = new Cliente(
                    "Billy",
                    "billy.batson@gmail.com",
                    "38060025090",
                    "(62) 39020-1931",
                    "ihzNM37gF",
                    of(1998, MARCH, 24),
                    CLIENTE
            );
            Animal soneca = new Animal(
                    "Soneca",
                    of(2010, APRIL, 15),
                    "American Bully",
                    billy
            );

            Administrador clark = new Administrador(
                    "Clark",
                    "clark.kent@gmail.com",
                    "83463261065",
                    "(62) 3731-6206",
                    "abcder",
                    of(1972, AUGUST, 3),
                    ADMINISTRADOR,
                    "gerente",
                    10000.00
            );

            clienteRepository.saveAll(
                    List.of(bruce, billy, hector, aquiles)
            );
            funcionarioRepository.save(barry);
            administradorRepository.save(clark);
            animalRepository.saveAll(
                    List.of(floquinho, soneca, luke, fumaca)
            );
            agendamentoService.agendarServico(agendamentoRequest);

        };
    }
}

