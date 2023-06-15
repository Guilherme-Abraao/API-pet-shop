package com.example.petshop.config;

import com.example.petshop.base.Animal;
import com.example.petshop.base.Cliente;
import com.example.petshop.base.Role;
import com.example.petshop.base.Usuario;
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

    @Bean
    CommandLineRunner commandLineRunner(UsuarioRepository usuarioRepository, AnimalRepository animalRepository) {
        return args -> {
            var bruce = Usuario.builder()
                    .nome("Bruce")
                    .email("bruce.wayne@gmail.com")
                    .cpf("71561133051")
                    .telefone("(11) 42852-9122")
                    .username("bruce")
                    .password("1l5O0mb4AN")
                    .dataNascimento(LocalDate.of(2000, JANUARY, 25))
                    .role(ADMINISTRADOR)
                    .build();
            System.out.println("Admin token::");

            /*Animal floquinho = new Animal(
                    "Floquinho",
                    15,
                    bruce
            );
            Animal jararaca = new Animal(
                    "Jararaca",
                    14,
                    bruce
            );*/

           /* Cliente barry = new Cliente(
                    "Barry",
                    "barry.allen@gmail.com",
                    "14180500086",
                    "(62) 22097-8318",
                    "PuZfPjDQo6",
                    LocalDate.of(1995, JULY, 14)
            );*/

            var barry = Usuario.builder()
                    .nome("Barry")
                    .email("barry.allen@gmail.com")
                    .cpf("14180500086")
                    .telefone("(62) 22097-8318")
                    .username("barry")
                    .password("PuZfPjDQo6")
                    .dataNascimento(LocalDate.of(1995, JULY, 14))
                    .role(CLIENTE)
                    .build();
            System.out.println("Client token::");

            /*Animal luke = new Animal(
                    "Luke",
                    5,
                    barry
            );*/

            /*Cliente billy = new Cliente(
                    "Billy",
                    "billy.batson@gmail.com",
                    "38060025090",
                    "(62) 39020-1931",
                    "iihzNM37gF",
                    LocalDate.of(1998, Month.MARCH, 24)
            );*/

            var billy = Usuario.builder()
                    .nome("Billy")
                    .email("billy.batson@gmail.com")
                    .cpf("38060025090")
                    .telefone("(62) 22097-8318")
                    .username("billy")
                    .password("iihzNM37gF")
                    .dataNascimento(LocalDate.of(1995, JULY, 14))
                    .role(FUNCIONARIO)
                    .build();
            System.out.println("Employee token::");

            /*Animal hector = new Animal(
                    "Hector",
                    8,
                    billy
            );*/
            usuarioRepository.saveAll(
                    List.of(bruce, barry, billy)
            );
            /*animalRepository.saveAll(
                    List.of(floquinho, jararaca, luke, hector)
            );*/

        };
    }
}
