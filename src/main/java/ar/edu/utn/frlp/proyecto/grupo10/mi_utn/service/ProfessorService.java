package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.ProfessorRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.ProfessorDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.ConflictException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract.ProfessorMapper;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.ProfessorRepository;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.contract.ProfessorContract;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfessorService implements ProfessorContract {

    private ProfessorRepository professorRepository;
    private ProfessorMapper professorMapper;

    @Override
    public List<ProfessorDTO> findAll(Integer from, Integer to) {
        if((from == null || from == 0) && (to == null || to == 0))
            return this.professorRepository.findAll().stream().map(professorMapper::toDTO).toList();
        if(from == null || from == 0){
            Pageable pageable = PageRequest.of(0,to);
            return this.professorRepository.findAll(pageable).stream().map(professorMapper::toDTO).toList();
        }
        Pageable pageable = PageRequest.of(from,to);
        return this.professorRepository.findAll(pageable).stream().map(professorMapper::toDTO).toList();
    }


    public void save(ProfessorRequestDTO professorRequestDTO){
        if(this.professorRepository.existsByNameAndLastname(professorRequestDTO.getName(),professorRequestDTO.getLastname())){
            throw new ConflictException("No puede haber registros duplicados");
        }
        this.professorRepository.save(this.professorMapper.toEntity(professorRequestDTO));
    }

    @Override
    public void update(ProfessorDTO professorDTO) throws BadRequestException {
        if(!this.professorRepository.existsById(professorDTO.getId()))
            throw new BadRequestException("Profesor no existente");

        this.professorRepository.save(this.professorMapper.toEntity(professorDTO));
    }

    @Override
    public void delete(Long id) throws BadRequestException {
        if(!this.professorRepository.existsById(id))
            throw new BadRequestException("Profesor no existente");
        this.professorRepository.deleteById(id);
    }

    @Override
    public ProfessorDTO findById(Long id) throws BadRequestException {
        return this.professorRepository.findById(id)
                .map(professorMapper::toDTO)
                .orElseThrow(BadRequestException::new);
    }

    @Override
    public List<ProfessorDTO> findByName(String name, Integer from, Integer to) {
        if((from == null || from == 0) && (to == null || to == 0))
            return this.professorRepository.findByName(name).stream().map(professorMapper::toDTO).toList();
        if(from == null || from == 0){
            Pageable pageable = PageRequest.of(0,to);
            return this.professorRepository.findByName(name,pageable).stream().map(professorMapper::toDTO).toList();
        }
        Pageable pageable = PageRequest.of(from,to);
        return this.professorRepository.findByName(name,pageable).stream().map(professorMapper::toDTO).toList();
    }

    @Override
    public List<ProfessorDTO> findByLastname(String lastname, Integer from, Integer to) {
        if((from == null || from == 0) && (to == null || to == 0))
            return this.professorRepository.findByLastname(lastname).stream().map(professorMapper::toDTO).toList();
        if(from == null || from == 0){
            Pageable pageable = PageRequest.of(0,to);
            return this.professorRepository.findByLastname(lastname,pageable).stream().map(professorMapper::toDTO).toList();
        }
        Pageable pageable = PageRequest.of(from,to);
        return this.professorRepository.findByLastname(lastname,pageable).stream().map(professorMapper::toDTO).toList();
    }

    @Override
    public List<ProfessorDTO> findByNameAndLastname(String name, String lastname) {
        return this.professorRepository.findByNameAndLastname(name,lastname).stream().map(professorMapper::toDTO).toList();
    }

    public Boolean existsById(Long id) {
        return this.professorRepository.existsById(id);
    }
}
