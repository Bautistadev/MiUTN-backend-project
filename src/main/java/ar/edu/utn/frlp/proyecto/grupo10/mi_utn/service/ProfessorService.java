package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.ProfessorDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract.ProfessorMapper;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.ProfessorRepository;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.contract.ProfessorContract;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfessorService implements ProfessorContract {

    private ProfessorRepository professorRepository;
    private ProfessorMapper professorMapper;

    @Override
    public List<ProfessorDTO> findAll() {
        return this.professorRepository.findAll().stream().map(professorMapper::toDTO).toList();
    }
}
