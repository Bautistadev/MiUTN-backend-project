package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "areas")
@PrimaryKeyJoinColumn(name = "nodo_id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Area extends Nodo {

    // 🔥 TODOS LOS PUNTOS DEL POLÍGONO
    @ElementCollection
    @CollectionTable(name = "area_puntos", joinColumns = @JoinColumn(name = "area_id"))
    @OrderColumn(name = "orden")
    private List<PuntoCoordenada> puntos = new ArrayList<>();

// Getters y Setters
}
