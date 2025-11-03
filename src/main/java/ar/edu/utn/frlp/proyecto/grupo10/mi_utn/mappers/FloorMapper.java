package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.FloorDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Floor;
import org.springframework.stereotype.Component;

@Component
public class FloorMapper {

    public FloorDTO toDto(Floor floor) {
        if (floor == null) {
            return null;
        }

        return FloorDTO.builder()
                .id(floor.getId())
                .name(floor.getName())
                .build();
    }

    public Floor toEntity(FloorDTO floorDTO) {
        if (floorDTO == null) {
            return null;
        }

        return Floor.builder()
                .id(floorDTO.getId())
                .name(floorDTO.getName())
                .build();
    }
}