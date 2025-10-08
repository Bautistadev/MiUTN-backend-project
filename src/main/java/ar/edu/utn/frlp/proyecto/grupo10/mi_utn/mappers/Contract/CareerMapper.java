package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.CareerRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.CareerDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Career;

public interface CareerMapper {
    public Career toEntity(CareerDTO careerDTO);
    public Career toEntity(CareerRequestDTO careerRequestDTO);
    public CareerDTO toDTO(Career career);
}
