package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Floor.java - Similar a Career
@Entity
@Table(name = "floor")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Floor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name; // "Planta Principal", "Piso 1", "Piso 2", etc.

}