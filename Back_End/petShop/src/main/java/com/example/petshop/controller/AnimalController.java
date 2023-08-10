package com.example.petshop.controller;

import com.example.petshop.base.Animal;
import com.example.petshop.exception.UserException;
import com.example.petshop.repository.AnimalRepository;
import com.example.petshop.repository.ClienteRepository;
import com.example.petshop.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/petshop/animal")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public ResponseEntity<List<Animal>> getAllAnimais() {
        List<Animal> animais = animalService.getAnimais();
        return new ResponseEntity<>(animais, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable("id") Long id) throws UserException {
        Animal animal = animalService.findAnimalById(id);
        return new ResponseEntity<>(animal, HttpStatus.OK);
    }

    @PostMapping(path = "/cadastrarAnimal/{clienteId}")
    public ResponseEntity<Animal> cadastrarAnimal(
            @RequestBody Animal animal,
            @PathVariable Long clienteId
    ) throws UserException {
        Animal novoAnimal = animalService.cadastrarAnimal(animal, clienteId);
        return new ResponseEntity<>(novoAnimal, HttpStatus.CREATED);
    }
}
