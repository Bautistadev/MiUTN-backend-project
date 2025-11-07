package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasilloDTO {
    private Long id;
    private String nombre;
    private Long desdeNodoId;
    private Long hastaNodoId;
    private String planoId;
    private Long careerId;
    private Long floorId;
    private Double distancia;
}