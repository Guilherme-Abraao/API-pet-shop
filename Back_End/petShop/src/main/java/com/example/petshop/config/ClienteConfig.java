package com.example.petshop.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import com.example.petshop.repository.ClienteRepository;
import com.example.petshop.repository.AnimalRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import com.example.petshop.base.Cliente;
import com.example.petshop.base.Animal;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

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
    CommandLineRunner commandLineRunner(ClienteRepository clienteRepository, AnimalRepository animalRepository) {
        return args -> {
            Cliente bruce = new Cliente(
                    "Bruce",
                    "bruce.wayne@gmail.com",
                    "71561133051",
                    "(11) 42852-9122",
                    "1l5O0mb4AN",
                    LocalDate.of(2000, Month.JANUARY, 25)
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

            Cliente barry = new Cliente(
                    "Barry",
                    "barry.allen@gmail.com",
                    "14180500086",
                    "(62) 22097-8318",
                    "PuZfPjDQo6",
                    LocalDate.of(1995, Month.JULY, 14)
            );

            Animal luke = new Animal(
                    "Luke",
                    5,
                    barry
            );

            Cliente billy = new Cliente(
                    "Billy",
                    "billy.batson@gmail.com",
                    "38060025090",
                    "(62) 39020-1931",
                    "iihzNM37gF",
                    LocalDate.of(1998, Month.MARCH, 24)
            );

            Animal hector = new Animal(
                    "Hector",
                    8,
                    billy
            );

            clienteRepository.saveAll(
                    List.of(bruce, barry, billy)
            );
            animalRepository.saveAll(
                    List.of(floquinho, jararaca, luke, hector)
            );

        };
    }
}
