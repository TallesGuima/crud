package com.example.Cadastro.controllers;

import com.example.Cadastro.models.User;
import com.example.Cadastro.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@Transactional
@ActiveProfiles(value = "test")
public class UserControllerTest {

    @InjectMocks
    UserController controller;

    @Mock
    private UserRepository repository;


    User user;
    User user1;
    User user2;
    @BeforeEach
    public void setUp(){
        user = new User("JohnDoe", "john@gmail.com", "123456", true);
        user1 = new User("JaneDoe", "jane@gmail.com", "123456789", true);
        user2 = new User("JaneDoe", "janedoe@gmail.com", "123", true);
    }

    private void tests(Object userObject, Object userObjectTest){
        assertNotNull(userObject);
        assertEquals(userObjectTest, userObject);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void testFindAllUsersReturnsExpectedList(){
        List<User> users = List.of(user, user1, user2);
        when(repository.findAll()).thenReturn(users);
        ResponseEntity<List<User>> usersTest = controller.getAllUsers();

        assertEquals(HttpStatus.OK, usersTest.getStatusCode());
        tests(users, usersTest.getBody());
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
        List<User> users = List.of(user1, user2);
        when(repository.findByName(user1.getName())).thenReturn(users);
        List<User> userTestMultipleNameUsers = controller.getUserByName(user1.getName()).getBody();

        tests(users, userTestMultipleNameUsers);
        verify(repository).findByName(this.user1.getName());


        when(repository.findByName(user.getName())).thenReturn(List.of(user));
        List<User> userTestUniqueNameUser = controller.getUserByName(this.user.getName()).getBody();

        tests(this.user, userTestUniqueNameUser.get(0));
        verify(repository).findByName(this.user.getName());
    }
}
