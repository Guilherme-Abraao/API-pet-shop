package com.example.petshop.config;

import com.example.petshop.agendamento.AgendamentoRepository;
import com.example.petshop.agendamento.AgendamentoRequest;
import com.example.petshop.agendamento.AgendamentoService;
import com.example.petshop.base.*;
import com.example.petshop.repository.AdministradorRepository;
import com.example.petshop.repository.AnimalRepository;
import com.example.petshop.repository.ClienteRepository;
import com.example.petshop.repository.FuncionarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import static com.example.petshop.agendamento.Servico.*;
import static com.example.petshop.base.Cargo.gerentePetshop;
import static com.example.petshop.base.Cargo.recepcionistaVeterinario;
import static com.example.petshop.base.Role.*;
import static java.time.LocalDate.of;
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
            AgendamentoService agendamentoService,
            AgendamentoRepository agendamentoRepository
    ) {
        return args -> {
            Administrador clark = new Administrador(
                    "Clark",
                    "clark.kent@gmail.com",
                    "83463261065",
                    "(62) 3731-6206",
                    "abcder",
                    of(1972, AUGUST, 3),
                    gerentePetshop,
                    10000.00
            );

            administradorRepository.save(clark);

            Funcionario barry = new Funcionario(
                    "Barry",
                    "barry.allen@gmail.com",
                    "14180500086",
                    "(62) 22097-8318",
                    "PuZfPjDQo6",
                    of(1995, JULY, 14),
                    recepcionistaVeterinario,
                    1045.65
            );

            Funcionario james = new Funcionario(
                    "James da Salada de Fruta",
                    "james@gmail.com",
                    "53583299000",
                    "(62) 984237092",
                    "9XklIxxOsRZ2pe",
                    of(1969, APRIL, 20),
                    Cargo.auxiliarEstoque,
                    15000.00
            );

            funcionarioRepository.saveAll(List.of(barry, james));

            Cliente bruce = new Cliente(
                    "Bruce",
                    "bruce.wayne@gmail.com",
                    "71561133051",
                    "(11) 42852-9122",
                    "1l5O0mb4AN",
                    of(2000, JANUARY, 25)
            );

            Cliente hector = new Cliente(
                    "Hector",
                    "hector@gmail.com",
                    "07228319001",
                    "(11) 42852-9122",
                    "1l5O0mb4AN",
                    of(2000, JANUARY, 25)
            );

            Cliente aquiles = new Cliente(
                    "Aquiles",
                    "aquiles@gmail.com",
                    "70494074108",
                    "(11) 42852-9122",
                    "1l5O0mb4AN",
                    of(2000, JANUARY, 25)
            );

            Cliente billy = new Cliente(
                    "Billy",
                    "billy.batson@gmail.com",
                    "38060025090",
                    "(62) 39020-1931",
                    "ihzNM37gF",
                    of(1998, MARCH, 24)
            );

            clienteRepository.saveAll(
                    List.of(bruce, billy, hector, aquiles)
            );

            Logger loggerBruce = Logger.getLogger(Cliente.class.getName());
            loggerBruce.info("bruce: " + bruce.getId());
            loggerBruce.info("bruce: " + bruce.getNome());

            Logger loggerBilly = Logger.getLogger(Cliente.class.getName());
            loggerBilly.info("billy: " + billy.getId());
            loggerBilly.info("billy: " + billy.getNome());

            Logger loggerHector = Logger.getLogger(Cliente.class.getName());
            loggerHector.info("hector: " + hector.getId());
            loggerHector.info("hector: " + hector.getNome());

            Logger loggerAquiles = Logger.getLogger(Cliente.class.getName());
            loggerAquiles.info("aquiles: " + aquiles.getId());
            loggerAquiles.info("aquiles: " + aquiles.getNome());

            Animal floquinho = new Animal(
                    "Floquinho",
                    of(2015, FEBRUARY, 13),
                    "Basset hound",
                    "Cachorro",
                    bruce
            );

            Animal luke = new Animal(
                    "Luke",
                    of(2014, NOVEMBER, 5),
                    "Basenji",
                    "Cachorro",
                    bruce
            );

            Animal fumaca = new Animal(
                    "Fumaça",
                    of(2013, OCTOBER, 10),
                    "Akita",
                    "Cachorro",
                    hector
            );

            Animal soneca = new Animal(
                    "Soneca",
                    of(2010, APRIL, 15),
                    "American Bully",
                    "Cachorro",
                    billy
            );

            Animal ace = new Animal(
                    "Ace",
                    of(2010, APRIL, 15),
                    "American Bully",
                    "Cachorro",
                    aquiles
            );

            animalRepository.saveAll(
                    List.of(floquinho, soneca, luke, fumaca, ace)
            );

            AgendamentoRequest agendarFloquinho = new AgendamentoRequest(
                    LocalDateTime.of(2023, JUNE, 10, 13, 30), // dataHoraStart
                    bruce.getId(), // clienteId
                    floquinho.getId(), // animalId
                    List.of(hidratacao, unha),
                    barry.getId() // funcionarioId
            );

            AgendamentoRequest agendarFumaca = new AgendamentoRequest(
                    LocalDateTime.of(2023, JUNE, 10, 14, 30), // dataHoraStart
                    hector.getId(), // clienteId
                    fumaca.getId(), // animalId
                    List.of(banho, dentes),
                    "Ele tem carrapicho.",
                    barry.getId() // funcionarioId
            );

            agendamentoService.agendarServicos(List.of(agendarFloquinho, agendarFumaca));
//            agendamentoService.agendarServicos(agendarFumaca);

        };
    }
}

