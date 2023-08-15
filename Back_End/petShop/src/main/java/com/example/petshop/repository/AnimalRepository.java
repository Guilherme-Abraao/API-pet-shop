package com.example.petshop.repository;

import com.example.petshop.base.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("SELECT a FROM Animal a WHERE a.nome = ?1")
    Optional<Animal> findAnimalByName(String name);

}
