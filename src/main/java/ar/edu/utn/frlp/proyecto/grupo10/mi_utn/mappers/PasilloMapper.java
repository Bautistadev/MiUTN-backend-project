package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.PasilloDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Career;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Floor;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Nodo;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Pasillo;
import org.springframework.stereotype.Component;

@Component
public class PasilloMapper {

    public PasilloDTO toDto(Pasillo pasillo) {
        if (pasillo == null) {
            return null;
        }

        PasilloDTO dto = new PasilloDTO();
        dto.setId(pasillo.getId());
        dto.setNombre(pasillo.getNombre());
        dto.setDesdeNodoId(pasillo.getDesde().getId());
        dto.setHastaNodoId(pasillo.getHasta().getId());
        dto.setPlanoId(pasillo.getPlanoId());
        dto.setCareerId(pasillo.getCareer().getId());
        dto.setFloorId(pasillo.getFloor().getId());
        dto.setDistancia(pasillo.getDistancia());

        return dto;
    }

    public Pasillo toEntity(PasilloDTO dto, Nodo desde, Nodo hasta, Career career, Floor floor) {
        if (dto == null) {
            return null;
        }

        Pasillo pasillo = new Pasillo();
        pasillo.setId(dto.getId());
        pasillo.setNombre(dto.getNombre());
        pasillo.setDesde(desde);
        pasillo.setHasta(hasta);
        pasillo.setPlanoId(dto.getPlanoId());
        pasillo.setCareer(career);
        pasillo.setFloor(floor);
        pasillo.setDistancia(dto.getDistancia());

        return pasillo;
    }
}