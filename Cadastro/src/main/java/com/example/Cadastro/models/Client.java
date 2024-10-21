package com.example.Cadastro.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_clients")
public class Client {

    @Id
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String adress;
    @ManyToOne
    @JoinColumn(nullable = false)
    private City city;
    @Column(nullable = false)
    private boolean active;
    @Column(nullable = false)
    private LocalDate birthday;
    @OneToMany
    @JoinColumn
    private List<Child> children;
}
