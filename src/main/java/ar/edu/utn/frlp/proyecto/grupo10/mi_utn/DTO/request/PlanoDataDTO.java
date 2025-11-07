package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// PlanoDataDTO.java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanoDataDTO {
    private String planoId;
    private List<NodoDTO> nodos;
    private List<PasilloDTO> pasillos;
}