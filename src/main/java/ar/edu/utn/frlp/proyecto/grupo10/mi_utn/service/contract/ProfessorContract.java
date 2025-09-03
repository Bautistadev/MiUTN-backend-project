package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.contract;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.ProfessorRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.ProfessorDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;

import java.util.List;

public interface ProfessorContract {

    List<ProfessorDTO> findAll(Integer from, Integer to);
    void save(ProfessorRequestDTO professorRequestDTO);
    void update(ProfessorDTO professorDTO) throws BadRequestException;
    void delete(Long id) throws BadRequestException;
    ProfessorDTO findById(Long id) throws BadRequestException;
    List<ProfessorDTO> findByName(String name, Integer from, Integer to);
    List<ProfessorDTO> findByLastname(String lastname, Integer from, Integer to);
    List<ProfessorDTO> findByNameAndLastname(String name, String lastname);
}
