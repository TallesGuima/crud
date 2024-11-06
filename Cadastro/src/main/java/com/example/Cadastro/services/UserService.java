package com.example.Cadastro.services;

import com.example.Cadastro.dtos.UserDTO;
import com.example.Cadastro.models.User;
import com.example.Cadastro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAllUser(){
        return repository.findAll();
    }

    public User getUserById(UUID id){
        Optional<User> user = repository.findById(id);
        if (user.isEmpty()) {
            return null;
        } else {
            return user.get();
        }
    }

    public List<User> getUserByName(String name){
        List<User> users = repository.findByName(name);
        if (users.isEmpty()) {
            return null;
        } else {
            return users;
        }
    }

    public User postUser(UserDTO userDTO){
        User user = new User(userDTO.getName(), userDTO.getLogin(), userDTO.getPassword(), true);
        repository.save(user);
        return user;
    }

    public User putUser(UUID id, UserDTO userDTO){
        if(!(repository.existsById(id))){
            return null;
        } else {
            User user = repository.findById(id).get();

            user.setName(userDTO.getName());
            user.setLogin(userDTO.getLogin());
            user.setPassword(userDTO.getPassword());
            user.setActive(userDTO.isActive());

            repository.save(user);
            return user;
        }
    }

    public HttpStatus deleteUser(UUID id){
        if(!(repository.existsById(id))){
            return HttpStatus.NOT_FOUND;
        } else {
            repository.deleteById(id);
            return HttpStatus.NO_CONTENT;
        }
    }


}


