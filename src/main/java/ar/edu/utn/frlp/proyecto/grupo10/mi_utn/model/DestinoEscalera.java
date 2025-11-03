package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DestinoEscalera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "escalera_id", nullable = false)
    private Escalera escalera;

    @ManyToOne
    @JoinColumn(name = "career_destino_id", nullable = false)
    private Career carreraDestino;

    @ManyToOne
    @JoinColumn(name = "floor_destino_id", nullable = false)
    private Floor pisoDestino;

    // Orden de prioridad si es necesario
    private Integer orden;

    // Getters y Setters
}