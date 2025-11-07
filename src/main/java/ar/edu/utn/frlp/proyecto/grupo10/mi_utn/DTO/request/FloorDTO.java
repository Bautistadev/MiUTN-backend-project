package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FloorDTO {
    private Long id;
    private String name;
}