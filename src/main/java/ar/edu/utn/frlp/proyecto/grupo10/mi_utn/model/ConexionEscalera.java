package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConexionEscalera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "escalera_origen_id", nullable = false)
    private Escalera escaleraOrigen;

    @ManyToOne
    @JoinColumn(name = "escalera_destino_id", nullable = false)
    private Escalera escaleraDestino;

    // Tipo de conexión (directa, intermedia, etc.)


    // Getters y Setters
}