package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Enum.DireccionEscalera;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "escaleras")
@PrimaryKeyJoinColumn(name = "nodo_id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Escalera extends Area {

    // 🔥 USANDO TUS ENTIDADES
    @ManyToOne
    @JoinColumn(name = "career_actual_id", nullable = false)
    private Career carreraActual;

    @ManyToOne
    @JoinColumn(name = "floor_actual_id", nullable = false)
    private Floor pisoActual;

    @ManyToOne
    @JoinColumn(name = "career_destino_id", nullable = false)
    private Career carreraDestino;

    @ManyToOne
    @JoinColumn(name = "floor_destino_id", nullable = false)
    private Floor pisoDestino;

    @Enumerated(EnumType.STRING)
    private DireccionEscalera direccion;

    @ManyToOne
    @JoinColumn(name = "escalera_conectada_id")
    private Escalera escaleraConectada;

    // Getters y Setters
}