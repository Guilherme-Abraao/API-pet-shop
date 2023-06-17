package com.example.petshop.config;

import com.example.petshop.base.Administrador;
import com.example.petshop.base.Animal;
import com.example.petshop.base.Cliente;
import com.example.petshop.base.Funcionario;
import com.example.petshop.repository.AnimalRepository;
import com.example.petshop.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static com.example.petshop.base.Role.*;
import static java.time.Month.JANUARY;
import static java.time.Month.JULY;

@Configuration
public class ClienteConfig {

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

    /*@Bean
    CommandLineRunner commandLineRunner(AuthenticationService service) {
        return args -> {
            var bruce = RegisterRequest.builder()
                    .nome("Bruce")
                    .email("bruce.wayne@gmail.com")
                    .cpf("71561133051")
                    .telefone("(11) 42852-9122")
                    .username("bruce")
                    .password("1l5O0mb4AN")
                    .dataNascimento(LocalDate.of(2000, JANUARY, 25))
                    .role(ADMINISTRADOR)
                    .build();
            System.out.println("Admin token::" + service.register(bruce).getAccessToken());

            var barry = RegisterRequest.builder()
                    .nome("Barry")
                    .email("barry.allen@gmail.com")
                    .cpf("14180500086")
                    .telefone("(62) 22097-8318")
                    .username("barry")
                    .password("PuZfPjDQo6")
                    .dataNascimento(LocalDate.of(1995, JULY, 14))
                    .role(CLIENTE)
                    .build();
            System.out.println("Client token::" + service.register(barry).getAccessToken());

            var billy = RegisterRequest.builder()
                    .nome("Billy")
                    .email("billy.batson@gmail.com")
                    .cpf("38060025090")
                    .telefone("(62) 22097-8318")
                    .username("billy")
                    .password("iihzNM37gF")
                    .dataNascimento(LocalDate.of(1995, JULY, 14))
                    .role(FUNCIONARIO)
                    .build();
            System.out.println("Employee token::" + service.register(billy).getAccessToken());
        };
    }*/

    @Bean
    CommandLineRunner commandLineRunner(UsuarioRepository usuarioRepository, AnimalRepository animalRepository) {
        return args -> {
            Cliente bruce = new Cliente(
                    "Bruce",
                    "bruce.wayne@gmail.com",
                    "71561133051",
                    "(11) 42852-9122",
                    "1l5O0mb4AN",
                    LocalDate.of(2000, JANUARY, 25),
                    CLIENTE
            );

            Cliente hector = new Cliente(
                    "Hector",
                    "hector@gmail.com",
                    "08174858130",
                    "(11) 42852-9122",
                    "1l5O0mb4AN",
                    LocalDate.of(2000, JANUARY, 25),
                    CLIENTE
            );

            Cliente aquiles = new Cliente(
                    "Aquiles",
                    "aquiles@gmail.com",
                    "70494074108",
                    "(11) 42852-9122",
                    "1l5O0mb4AN",
                    LocalDate.of(2000, JANUARY, 25),
                    CLIENTE
            );

            Animal floquinho = new Animal(
                    "Floquinho",
                    15,
                    bruce
            );
            Animal jararaca = new Animal(
                    "Jararaca",
                    14,
                    bruce
            );

            Funcionario barry = new Funcionario(
                    "Barry",
                    "barry.allen@gmail.com",
                    "14180500086",
                    "(62) 22097-8318",
                    "PuZfPjDQo6",
                    LocalDate.of(1995, JULY, 14),
                    FUNCIONARIO,
                    "recepcionista",
                    1045.65
            );

            Animal luke = new Animal(
                    "Luke",
                    5,
                    bruce
            );

            Cliente billy = new Cliente(
                    "Billy",
                    "billy.batson@gmail.com",
                    "38060025090",
                    "(62) 39020-1931",
                    "iihzNM37gF",
                    LocalDate.of(1998, Month.MARCH, 24),
                    CLIENTE
            );

            /*Animal hector = new Animal(
                    "Hector",
                    8,
                    billy
            );*/
            usuarioRepository.saveAll(
                    List.of(bruce, barry, billy, hector, aquiles)
            );
            animalRepository.saveAll(
                    List.of(floquinho, jararaca, luke)
            );

        };
    }
}

