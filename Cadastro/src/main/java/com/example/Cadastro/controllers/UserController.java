package com.example.Cadastro.controllers;

import com.example.Cadastro.dtos.UserDTO;
import com.example.Cadastro.models.User;
import com.example.Cadastro.repositories.UserRepository;
import com.example.Cadastro.services.UserService;
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
import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository repository;


    @Operation(description = "Busca todos os usuários")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna todos os usuários"),
        @ApiResponse(responseCode = "500", description = "Algo deu errado"),
        @ApiResponse(responseCode = "400", description = "Sem corpo", content = @Content),
    })
    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        System.out.println(userService.getAllUser().get(0));
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(description = "Registra um usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dado(s) de usuário incorreto(s)"),
        @ApiResponse(responseCode = "500", description = "Algo deu errado")
    })
    public ResponseEntity<User> postUser(@Valid @RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.postUser(userDTO), HttpStatus.CREATED);
    }

    @Operation(description = "Busca um usuário pelo id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Usuário encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado, id está errado"),
        @ApiResponse(responseCode = "500", description = "Algo deu errado"),
        @ApiResponse(responseCode = "400", description = "Sem corpo", content = @Content)
    })
    @GetMapping(value = "/findbyid/{id}")
    public ResponseEntity<User> findUserById(@PathVariable UUID id) {
        User user = userService.getUserById(id);
        if(user.getId() == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        }
    }

    @Operation(description = "Busca vários usuários pelo nome")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "302", description = "Usuários encontrados"),
        @ApiResponse(responseCode = "404", description = "Usuários não foram encontrados, nome está errado"),
        @ApiResponse(responseCode = "500", description = "Algo deu errado"),
        @ApiResponse(responseCode = "400", description = "Sem corpo", content = @Content)
    })
    @GetMapping(value = "/findbyname/{name}")
    public ResponseEntity<List<User>> findUserByName(@PathVariable String name) {
        List<User> users = userService.getUserByName(name);
        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(users, HttpStatus.FOUND);
        }
    }

    @Operation(description = "Faz o update de um usuário encontrado pelo id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário modificado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuários não foram encontrados, verifique o id"),
        @ApiResponse(responseCode = "400", description = "Dado(s) de usuário incorreto(s)"),
        @ApiResponse(responseCode = "500", description = "Algo deu errado")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @Valid @RequestBody UserDTO userDTO) {
        User user = userService.putUser(id, userDTO);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
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
        return new ResponseEntity<>(userService.deleteUser(id));
    }


}
