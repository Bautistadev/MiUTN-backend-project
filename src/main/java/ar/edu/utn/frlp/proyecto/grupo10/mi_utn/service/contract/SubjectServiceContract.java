package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.contract;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.ProfessorRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.SubjectRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.ProfessorDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.SubjectDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Subject;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;
import java.util.Optional;

public interface SubjectServiceContract {
    public SubjectDTO findById(Long id) throws BadRequestException;
    List<SubjectDTO> findAll(Integer from, Integer to);
    void save(SubjectRequestDTO subjectRequestDTO);
    void update(SubjectDTO subjectDTO) throws BadRequestException;
    void delete(Long id) throws BadRequestException;
    public List<SubjectDTO> findByCommissionId(Long commissionId, Integer from, Integer to);
    public List<SubjectDTO> findByYear(Integer year, Integer from, Integer to);
    public List<SubjectDTO> findByType(String type, Integer from, Integer to);
    public List<SubjectDTO> findByCareerId(Long careerId,Integer from, Integer to);
    public List<SubjectDTO> findByCareerName(String name,Integer from, Integer to);
    public SubjectDTO findByName(String name) throws BadRequestException;
}
