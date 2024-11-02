package com.example.Cadastro.controllers;

import com.example.Cadastro.models.User;
import com.example.Cadastro.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(description = "Busca todos os usuários")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna todos os usuários"),
        @ApiResponse(responseCode = "500", description = "Algo deu errado"),
        @ApiResponse(responseCode = "400", description = "Sem corpo", content = @Content),
    })
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = repository.findAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PostMapping
    @Operation(description = "Registra um usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dado(s) de usuário incorreto(s)"),
        @ApiResponse(responseCode = "500", description = "Algo deu errado")
    })
    public ResponseEntity<User> postUser(@Valid @RequestBody User user){

        User newUser = repository.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @Operation(description = "Busca um usuário pelo id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Usuário encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado, id está errado"),
        @ApiResponse(responseCode = "500", description = "Algo deu errado"),
        @ApiResponse(responseCode = "400", description = "Sem corpo", content = @Content)
    })
    @GetMapping(value = "/findbyid/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable UUID id) {
        Optional<User> userById = repository.findById(id);
        return userById.isPresent() ?
                new ResponseEntity<>(userById, HttpStatus.FOUND)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(description = "Busca vários usuários pelo nome")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Usuários encontrados"),
        @ApiResponse(responseCode = "404", description = "Usuários não foram encontrados, nome está errado"),
        @ApiResponse(responseCode = "500", description = "Algo deu errado"),
        @ApiResponse(responseCode = "400", description = "Sem corpo", content = @Content)
    })
    @GetMapping(value = "/findbyname/{name}")
    public ResponseEntity<List<User>> getUserByName(@PathVariable String name) {
        List<User> userByName = repository.findByName(name);

        return userByName.isEmpty() ?
            new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(userByName, HttpStatus.FOUND);

    }

    @Operation(description = "Faz o update de um usuário encontrado pelo id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário modificado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuários não foram encontrados, verifique o id"),
        @ApiResponse(responseCode = "400", description = "Dado(s) de usuário incorreto(s)"),
        @ApiResponse(responseCode = "500", description = "Algo deu errado")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @Valid @RequestBody User user) {
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
    @Operation(description = "Deleta um usuário pelo id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado, verifique o id"),
        @ApiResponse(responseCode = "500", description = "Algo deu errado"),
        @ApiResponse(responseCode = "400", description = "Sem corpo", content = @Content)
    })
    public ResponseEntity deleteUser(@PathVariable UUID id){
        if(repository.findById(id).isPresent()){
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
