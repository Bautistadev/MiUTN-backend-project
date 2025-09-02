package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.CareerRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.CareerDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract.CareerMapper;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Career;
import org.springframework.stereotype.Component;

@Component
public class CareerMapperImp implements CareerMapper {
    @Override
    public Career toEntity(CareerDTO careerDTO) {
        return Career.builder()
                .id(careerDTO.getId())
                .name(careerDTO.getName())
                .date(careerDTO.getDate())
                .dateUpdate(careerDTO.getDateUpdate())
                .dateDeleted(careerDTO.getDateDeleted())
                .build();
    }

    @Override
    public Career toEntity(CareerRequestDTO careerRequestDTO) {
        return Career.builder()
                .name(careerRequestDTO.getName())
                .build();
    }

    @Override
    public CareerDTO toDTO(Career career) {
        return CareerDTO.builder()
                .name(career.getName())
                .id(career.getId())
                .dateDeleted(career.getDateDeleted())
                .dateUpdate(career.getDateUpdate())
                .date(career.getDate())
                .build();
    }
}
