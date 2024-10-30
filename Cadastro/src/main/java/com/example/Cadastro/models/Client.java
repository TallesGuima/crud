package com.example.Cadastro.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
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
