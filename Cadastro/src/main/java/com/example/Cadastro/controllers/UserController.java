package com.example.Cadastro.controllers;

import com.example.Cadastro.models.User;
import com.example.Cadastro.repositories.UserRepository;
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
    public ResponseEntity<User> postUser(@RequestBody User user){
        user.setId(UUID.randomUUID());
        User newUser = repository.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping(value = "/findbyid/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable UUID id) {
        Optional<User> userById = repository.findById(id);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }

    @GetMapping(value = "/findbyname/{name}")
    public ResponseEntity<Optional<User>> getUserByName(@PathVariable String name) {
        Optional<User> userByName = repository.findByName(name);
        return new ResponseEntity<>(userByName, HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
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

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable UUID id){
        Optional<User> userToDelete = repository.findById(id);
        if(userToDelete.isPresent()){
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
