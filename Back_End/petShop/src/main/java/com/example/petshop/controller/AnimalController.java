package com.example.petshop.controller;

import com.example.petshop.base.Animal;
import com.example.petshop.base.AnimalRegisterRequest;
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
            @RequestBody AnimalRegisterRequest animal,
            @PathVariable Long clienteId
    ) throws UserException {
        Animal novoAnimal = animalService.cadastrarAnimal(animal, clienteId);
        return new ResponseEntity<>(novoAnimal, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deletarAnimal/{animalId}")
    public ResponseEntity<Animal> deletarAnimal(
            @PathVariable("animalId") Long animalId
    ) {
        animalService.deletarAnimal(animalId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/atualizarAnimal/{animalId}/{clienteId}")
    public ResponseEntity<Animal> atualizarAnimal(
            @PathVariable("animalId") Long animalId,
            @PathVariable("clienteId") Long clienteId,
            @RequestBody AnimalRegisterRequest animal
    ) {
        Animal animalAtualizado = animalService.atualizarAnimal(animalId, clienteId, animal);
        return new ResponseEntity<>(animalAtualizado, HttpStatus.OK);
    }
}
