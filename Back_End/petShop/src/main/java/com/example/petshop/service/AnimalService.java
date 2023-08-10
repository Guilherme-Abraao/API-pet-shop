package com.example.petshop.service;

import com.example.petshop.base.Animal;
import com.example.petshop.base.Cliente;
import com.example.petshop.exception.UserException;
import com.example.petshop.repository.AnimalRepository;
import com.example.petshop.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AnimalService {

    private final ClienteRepository clienteRepository;
    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(ClienteRepository clienteRepository, AnimalRepository animalRepository) {
        this.clienteRepository = clienteRepository;
        this.animalRepository = animalRepository;
    }

    public List<Animal> getAnimais() {
        return animalRepository.findAll();
    }

    public Animal findAnimalById(Long animalId) throws UserException {
        return animalRepository.findById(animalId)
                .orElseThrow(() -> new UserException(
                        "Animal com id " + animalId + " não existe."
                ));
    }

    public Animal cadastrarAnimal(Animal animal, Long clienteId) throws UserException {
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);

        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            animal.setCliente(cliente);
        } else {
            throw new UserException("Cliente com id " + clienteId + " não existe");
        }
        return animalRepository.save(animal);
    }

    public void deleteAnimal(Long animalId) {
        boolean exists = animalRepository.existsById(animalId);

        if (!exists) {
            throw new IllegalStateException("Animal com id " + animalId + " não existe.");
        }

        animalRepository.deleteById(animalId);
    }

   //Falta realizar testes com esse método
    public Animal atualizarAnimal(Long animalId, Long clienteId, String nome, Cliente cliente) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new IllegalStateException(
                        "Animal com id " + animalId + " já existe."
                ));

        if (nome != null &&
                clienteId != null &&
                !Objects.equals(animal.getNome(), nome)) {
            animal.setNome(nome);
        }

        if (cliente != null &&
                clienteId != null &&
                !Objects.equals(animal.getCliente(), cliente)) {
            Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);
            if (clienteOptional.isPresent()) {
                throw new IllegalStateException("Cliente já está vinculado ao seu animal");
            }
            animal.setCliente(cliente);
        }
        return animalRepository.save(animal);
    }

}
