package com.example.Cadastro.controllers;

import com.example.Cadastro.models.User;
import com.example.Cadastro.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    UserController controller;

    @Mock
    private UserRepository repository;

    User user;

    @BeforeEach
    public void setUp(){
        user = new User("Talles", "talles@gmail.com", "123456", true);
    }

    private void tests(Object userObject, User user){
        assertNotNull(userObject);
        assertEquals(user, userObject);
        verifyNoMoreInteractions(repository);
    }


    @Test
    public void testFindAllUsersReturnsExpectedList(){
        when(repository.findAll()).thenReturn(Collections.singletonList(user));
        ResponseEntity<List<User>> usersTest = controller.getAllUsers();

        assertEquals(HttpStatus.OK, usersTest.getStatusCode());
        tests(usersTest.getBody().get(0), user);
        verify(repository).findAll();
    }

    @Test
    public void testFindUserByIdExpectedObjectUser(){
        when(repository.findById(user.getId())).thenReturn(Optional.of(user));
        Optional<User> userTest = controller.getUserById(this.user.getId()).getBody();

        tests(userTest.get(), this.user);
        verify(repository).findById(this.user.getId());
    }

    @Test
    public void testFindUsersByNameExpectedObjectName(){
        when(repository.findByName(user.getName())).thenReturn(Optional.of(user));
        Optional<User> userTest = controller.getUserByName(this.user.getName()).getBody();

        tests(userTest.get(), user);
        verify(repository).findByName(this.user.getName());
    }

}
