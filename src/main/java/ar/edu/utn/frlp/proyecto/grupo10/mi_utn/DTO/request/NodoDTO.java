package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Enum.DireccionEscalera;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Enum.TipoNodo;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.PuntoCoordenada;

import java.util.List;

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

    // Para escaleras
    private Long carreraActualId;
    private Long pisoActualId;
    private Long carreraDestinoId;
    private Long pisoDestinoId;
    private DireccionEscalera direccion;
    private Long escaleraConectadaId;
}
