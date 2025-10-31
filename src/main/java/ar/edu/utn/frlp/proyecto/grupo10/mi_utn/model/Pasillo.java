package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pasillos")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Pasillo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "desde_nodo_id", nullable = false)
    private Nodo desde;

    @ManyToOne
    @JoinColumn(name = "hasta_nodo_id", nullable = false)
    private Nodo hasta;

    // 🔥 USANDO TUS ENTIDADES
    @ManyToOne
    @JoinColumn(name = "career_id", nullable = false)
    private Career career;

    @ManyToOne
    @JoinColumn(name = "floor_id", nullable = false)
    private Floor floor;

    @Column(nullable = false)
    private String planoId;

    private Double distancia;


    // Getters y Setters
}