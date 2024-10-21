package com.example.Cadastro.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_cities")
public class City {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(nullable = false)
    private State state;
}
