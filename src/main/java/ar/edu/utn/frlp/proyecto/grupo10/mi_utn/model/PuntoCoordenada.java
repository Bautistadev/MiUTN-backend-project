package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PuntoCoordenada {
    @Column(nullable = false)
    private Double x;

    @Column(nullable = false)
    private Double y;

    // Getters y Setters
}