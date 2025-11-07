package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Enum.DireccionEscalera;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "escaleras")
@PrimaryKeyJoinColumn(name = "nodo_id")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Escalera extends Area {

    @ManyToOne
    @JoinColumn(name = "career_actual_id", nullable = false)
    private Career carreraActual;

    @ManyToOne
    @JoinColumn(name = "floor_actual_id", nullable = false)
    private Floor pisoActual;

    // 🔥 MÚLTIPLES DESTINOS
    @OneToMany(mappedBy = "escalera", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DestinoEscalera> destinos = new ArrayList<>();

    // 🔥 MÚLTIPLES CONEXIONES
    @OneToMany(mappedBy = "escaleraOrigen", cascade = CascadeType.ALL)
    private List<ConexionEscalera> conexiones = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private DireccionEscalera direccion;

    // Getters y Setters
}