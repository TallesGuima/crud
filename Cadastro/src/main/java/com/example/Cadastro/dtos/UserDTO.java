package com.example.Cadastro.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    @NotBlank(message = "Name given must not be empty or null")
    @Size(min = 1, max=255, message = "Name given must have 1-255 characters")
    @Column(nullable = false)
    private String name;
    @Email(message = "email invalid")
    @Column(nullable = false)
    @NotBlank(message = "login given must not be empty or null")
    private String login;
    @NotBlank(message = "password given must not be empty or null")
    @Size(min = 10, max = 255 , message = "password given must have 10-255 characters")
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean active;
}
