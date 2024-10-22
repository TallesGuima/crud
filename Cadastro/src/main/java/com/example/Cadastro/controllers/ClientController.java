package com.example.Cadastro.controllers;

import com.example.Cadastro.models.Client;
import com.example.Cadastro.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    private ClientRepository repository;

    @GetMapping
    public ResponseEntity<List<Client>> findAllClient(){
        List<Client> allClient = repository.findAll();
        return new ResponseEntity<>(allClient, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> postClient(@RequestBody Client client){
        client.setId(UUID.randomUUID());
        repository.save(client);
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @GetMapping(value = "/findbyid/{id}")
    public ResponseEntity<Optional<Client>> findById(@PathVariable UUID id){
        Optional<Client> client = repository.findById(id);
        if(client.isPresent()){
            return new ResponseEntity<>(client, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/findbyname/{name}")
    public ResponseEntity<Optional<Client>> findByName(@PathVariable String name){
        Optional<Client> client = repository.findByName(name);
        if(client.isPresent()){
            return new ResponseEntity<>(client, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Client> updateClient(@RequestBody Client client, @PathVariable UUID id){
        Optional<Client> clientToUpdate = repository.findById(id);
        if(clientToUpdate.isPresent()){
            Client updateClient = clientToUpdate.get();

            updateClient.setName(client.getName());
            updateClient.setName(client.getName());
            updateClient.setBirthday(client.getBirthday());


            Client clientUpdated = repository.save(updateClient);
            return new ResponseEntity<>(clientUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Client> deleteChild(@PathVariable UUID id){
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
