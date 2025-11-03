package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Enum.DireccionEscalera;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Enum.TipoNodo;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.PuntoCoordenada;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NodoDTO {
    private Long id;
    private String nombre;
    private TipoNodo tipo;
    private String planoId;

    // 🔥 IDs DE LAS ENTIDADES EXISTENTES
    private Long careerId;
    private Long floorId;

    private Double coordenadaX;
    private Double coordenadaY;
    private List<PuntoCoordenada> puntos;

    // 🔥 NUEVA ESTRUCTURA PARA ESCALERAS
    private Long carreraActualId;
    private Long pisoActualId;
    private DireccionEscalera direccion;

    // 🔥 MÚLTIPLES DESTINOS (reemplaza los campos individuales)
    private List<Long> carrerasDestinoIds;
    private List<Long> pisosDestinoIds;

    // 🔥 MÚLTIPLES CONEXIONES (reemplaza el campo individual)
    private List<Long> escalerasConectadasIds;
}