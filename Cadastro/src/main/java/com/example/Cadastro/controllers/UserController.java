package com.example.Cadastro.controllers;

import com.example.Cadastro.models.User;
import com.example.Cadastro.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = repository.findAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> postUser(@Valid @RequestBody User user){

        User newUser = repository.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping(value = "/findbyid/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable UUID id) {
        Optional<User> userById = repository.findById(id);
        return userById.isPresent() ?
                new ResponseEntity<>(userById, HttpStatus.FOUND)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/findbyname/{name}")
    public ResponseEntity<List<User>> getUserByName(@PathVariable String name) {
        List<User> userByName = repository.findByName(name);

        return userByName.isEmpty() ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(userByName, HttpStatus.FOUND);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User user) {
        Optional<User> userToUpdate = repository.findById(id);
        if(userToUpdate.isPresent()){
            User userUpdate = userToUpdate.get();

            userUpdate.setName(user.getName());
            userUpdate.setLogin(user.getLogin());
            userUpdate.setPassword(user.getPassword());
            userUpdate.setActive(user.isActive());

            User userUpdated = repository.save(userUpdate);
            return new ResponseEntity<>(userUpdated, HttpStatus.OK);
        } else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUser(@PathVariable UUID id){
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
