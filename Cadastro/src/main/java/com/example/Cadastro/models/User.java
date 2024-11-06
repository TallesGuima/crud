package com.example.Cadastro.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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
    private String name;
    private String login;
    private String password;
    private boolean active;


    public User(String name, String login, String password, boolean active){
        setName(name);
        setLogin(login);
        setPassword(password);
        setActive(active);
    }
}
