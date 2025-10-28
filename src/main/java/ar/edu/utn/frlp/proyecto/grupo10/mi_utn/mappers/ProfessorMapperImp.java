package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.ProfessorRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.ProfessorDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract.ProfessorMapper;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapperImp implements ProfessorMapper {
    @Override
    public Professor toEntity(ProfessorDTO professorDTO) {
        return Professor.builder()
                .id(professorDTO.getId())
                .name(professorDTO.getName())
                .lastname(professorDTO.getLastname())
                .email(professorDTO.getEmail())
                .build();
    }

    @Override
    public Professor toEntity(ProfessorRequestDTO profesorRequestDTO) {
        return Professor.builder()
                .name(profesorRequestDTO.getName())
                .lastname(profesorRequestDTO.getLastname())
                .email(profesorRequestDTO.getEmail())
                .build();
    }

    @Override
    public ProfessorDTO toDTO(Professor profesor) {
        return ProfessorDTO.builder()
                .id(profesor.getId())
                .name(profesor.getName())
                .lastname(profesor.getLastname())
                .email(profesor.getEmail())
                .build();
    }
}
