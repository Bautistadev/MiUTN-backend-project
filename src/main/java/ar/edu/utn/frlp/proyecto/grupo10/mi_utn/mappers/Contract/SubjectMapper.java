package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.SubjectRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.SubjectDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Subject;

public interface SubjectMapper {
    public Subject toEntity(SubjectDTO subjectDTO);
    public Subject toEntity(SubjectRequestDTO subjectRequestDTO);
    public SubjectDTO toDTO(Subject subject);
}
