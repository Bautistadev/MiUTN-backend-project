package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.ProfessorRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.ProfessorDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Professor;

public interface ProfessorMapper{
    public Professor toEntity(ProfessorDTO professorDTO);
    public Professor toEntity(ProfessorRequestDTO profesorRequestDTO);
    public ProfessorDTO toDTO(Professor profesor);
}
