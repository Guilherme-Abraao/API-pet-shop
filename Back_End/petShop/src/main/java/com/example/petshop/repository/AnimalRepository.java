package com.example.petshop.repository;

import com.example.petshop.base.Animal;
import com.example.petshop.base.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

//    @Query("SELECT animal FROM Cliente.animais animal WHERE animal.nome = ?1")
////    Optional<Cliente> findAnimalByName(String name);

}
