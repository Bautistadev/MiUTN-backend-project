package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.contract;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.ProfessorDTO;

import java.util.List;

public interface ProfessorContract {

    public List<ProfessorDTO> findAll();
}
