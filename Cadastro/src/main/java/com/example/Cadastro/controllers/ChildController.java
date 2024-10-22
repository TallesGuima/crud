package com.example.Cadastro.controllers;

import com.example.Cadastro.models.Child;
import com.example.Cadastro.repositories.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/child")
public class ChildController {

    @Autowired
    private ChildRepository repository;

    @GetMapping
    public ResponseEntity<List<Child>> findAllChild(){
        List<Child> allChild = repository.findAll();
        return new ResponseEntity<>(allChild, HttpStatus.OK);
    }

    @GetMapping(value = "/findbyid/{id}")
    public ResponseEntity<Optional<Child>> findById(@PathVariable UUID id){
        Optional<Child> childById = repository.findById(id);
        if(childById.isPresent()) {
            return new ResponseEntity<>(childById, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/findbyname/{name}")
    public ResponseEntity<Optional<Child>> findByName(@PathVariable String name){
        Optional<Child> childByName = repository.findByName(name);
        if(childByName.isPresent()) {
            return new ResponseEntity<>(childByName, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Child> postChild(@RequestBody Child child){
        child.setId(UUID.randomUUID());
        Child newChild = repository.save(child);
        return new ResponseEntity<>(newChild, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Child> updateChild(@RequestBody Child child, @PathVariable UUID id){
        Optional<Child> childToUpdate = repository.findById(id);
        if(childToUpdate.isPresent()){
            Child updateChild = childToUpdate.get();

            updateChild.setName(child.getName());
            updateChild.setName(child.getName());
            updateChild.setBirthday(child.getBirthday());

            Child childUpdated = repository.save(updateChild);
            return new ResponseEntity<>(childUpdated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Child> deleteChild(@PathVariable UUID id){
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
