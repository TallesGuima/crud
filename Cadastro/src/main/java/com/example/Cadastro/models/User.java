package com.example.Cadastro.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
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


    public User(String name, String login, String password, boolean active){
        setName(name);
        setLogin(login);
        setPassword(password);
        setActive(active);
    }
}
