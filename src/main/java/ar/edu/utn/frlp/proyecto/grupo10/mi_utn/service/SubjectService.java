package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.SubjectRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.ScheduleMapDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.SubjectDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.SubjectMapDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.ConflictException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract.SubjectMapper;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Professor;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Schedule;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Subject;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.CommissionRespository;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.SubjectRepository;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.contract.SubjectServiceContract;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectService implements SubjectServiceContract {

    private SubjectRepository subjectRepository;
    private SubjectMapper subjectMapper;
    private ProfessorService professorService;
    private CommissionRespository commissionRespository;
    private CareerService careerService;


    @Override
    public SubjectDTO findById(Long id) throws BadRequestException {
        return this.subjectRepository.findById(id).map(this.subjectMapper::toDTO).orElseThrow(BadRequestException::new);
    }

    @Override
    public List<SubjectDTO> findAll(Integer from, Integer to) {
        if((from == null || from == 0) && (to == null || to == 0))
            return this.subjectRepository.findAll().stream().map(subjectMapper::toDTO).toList();
        if(from == null || from == 0){
            Pageable pageable = PageRequest.of(0,to);
            return this.subjectRepository.findAll(pageable).stream().map(subjectMapper::toDTO).toList();
        }
        Pageable pageable = PageRequest.of(from,to);
        return this.subjectRepository.findAll(pageable).stream().map(subjectMapper::toDTO).toList();
    }

    @Override
    public void save(SubjectRequestDTO subjectRequestDTO) {
        //VALIDAMOS QUE NO EXISTA LA MATERIA
        if(this.subjectRepository.existsByName(subjectRequestDTO.getName()))
            throw new ConflictException("Registro existente");

        //VALIDAMOS QUE TODOS LOS PROFESORES EXISTAN EN LA BASE DE DATOS
        subjectRequestDTO.getProfessorsId().stream()
                .filter(e->!this.professorService.existsById(e))
                .findFirst()
                .ifPresent(invalid->{
                    throw new IllegalArgumentException("Profesor no existente id: "+ invalid);
                });

        //VALIDAMOS QUE EXISTA LA COMISION
        if(!this.commissionRespository.existsById(subjectRequestDTO.getCommissionId()))
            throw new ConflictException("Comision no existente");

        //VALIDAMOS QUIE EXISTA LA CARRERA
        if(!this.careerService.existsById(subjectRequestDTO.getCareerId()))
            throw new IllegalArgumentException("Carrera no existente id: "+subjectRequestDTO.getCareerId());

        this.subjectRepository.save(this.subjectMapper.toEntity(subjectRequestDTO));

    }

    @Override
    public void update(SubjectDTO subjectDTO) throws BadRequestException {
        //VALIDAMOS QUE NO EXISTA LA MATERIA
        if(this.subjectRepository.existsByName(subjectDTO.getName()))
            throw new ConflictException("Registro existente");

        //VALIDAMOS QUE TODOS LOS PROFESORES EXISTAN EN LA BASE DE DATOS
        subjectDTO.getProfessors().stream()
                .filter(e->!this.professorService.existsById(e.getId()))
                .findFirst()
                .ifPresent(invalid->{
                    throw new IllegalArgumentException("Profesor no existente id: "+ invalid);
                });

        //VALIDAMOS QUE EXISTA LA COMISION
        if(!this.commissionRespository.existsById(subjectDTO.getCommission().getId()))
            throw new ConflictException("Comision no existente");

        //VALIDAMOS QUIE EXISTA LA CARRERA
        if(!this.careerService.existsById(subjectDTO.getCareer().getId()))
            throw new IllegalArgumentException("Carrera no existente id: "+subjectDTO.getCareer().getId());

        this.subjectRepository.save(this.subjectMapper.toEntity(subjectDTO));
    }

    @Override
    public void delete(Long id) throws BadRequestException {
        if(!this.subjectRepository.existsById(id))
            throw new BadRequestException("Registro no existente");

        this.subjectRepository.deleteById(id);
    }

    @Override
    public List<SubjectDTO> findByCommissionId(Long commissionId, Integer from, Integer to) {
        if((from == null || from == 0) && (to == null || to == 0))
            return this.subjectRepository.findByScheduleCommissionId(commissionId).stream().map(subjectMapper::toDTO).toList();
        if(from == null || from == 0){
            Pageable pageable = PageRequest.of(0,to);
            return this.subjectRepository.findByScheduleCommissionId(commissionId,pageable).stream().map(subjectMapper::toDTO).toList();
        }
        Pageable pageable = PageRequest.of(from,to);
        return this.subjectRepository.findByCareerId(commissionId,pageable).stream().map(subjectMapper::toDTO).toList();
    }


    @Override
    public List<SubjectDTO> findByYear(Integer year, Integer from, Integer to) {
        if((from == null || from == 0) && (to == null || to == 0))
            return this.subjectRepository.findByYear(year).stream().map(subjectMapper::toDTO).toList();
        if(from == null || from == 0){
            Pageable pageable = PageRequest.of(0,to);
            return this.subjectRepository.findByYear(year,pageable).stream().map(subjectMapper::toDTO).toList();
        }
        Pageable pageable = PageRequest.of(from,to);
        return this.subjectRepository.findByYear(year,pageable).stream().map(subjectMapper::toDTO).toList();
    }

    @Override
    public List<SubjectDTO> findByType(String type, Integer from, Integer to) {
        if((from == null || from == 0) && (to == null || to == 0))
            return this.subjectRepository.findByType(type).stream().map(subjectMapper::toDTO).toList();
        if(from == null || from == 0){
            Pageable pageable = PageRequest.of(0,to);
            return this.subjectRepository.findByType(type,pageable).stream().map(subjectMapper::toDTO).toList();
        }
        Pageable pageable = PageRequest.of(from,to);
        return this.subjectRepository.findByType(type,pageable).stream().map(subjectMapper::toDTO).toList();
    }

    @Override
    public List<SubjectDTO> findByCareerId(Long careerId, Integer from, Integer to) {
        if((from == null || from == 0) && (to == null || to == 0))
            return this.subjectRepository.findByCareerId(careerId).stream().map(subjectMapper::toDTO).toList();
        if(from == null || from == 0){
            Pageable pageable = PageRequest.of(0,to);
            return this.subjectRepository.findByCareerId(careerId,pageable).stream().map(subjectMapper::toDTO).toList();
        }
        Pageable pageable = PageRequest.of(from,to);
        return this.subjectRepository.findByCareerId(careerId,pageable).stream().map(subjectMapper::toDTO).toList();
    }

    @Override
    public List<SubjectDTO> findByCareerName(String name, Integer from, Integer to) {
        if((from == null || from == 0) && (to == null || to == 0))
            return this.subjectRepository.findByCareerName(name).stream().map(subjectMapper::toDTO).toList();
        if(from == null || from == 0){
            Pageable pageable = PageRequest.of(0,to);
            return this.subjectRepository.findByCareerName(name,pageable).stream().map(subjectMapper::toDTO).toList();
        }
        Pageable pageable = PageRequest.of(from,to);
        return this.subjectRepository.findByCareerName(name,pageable).stream().map(subjectMapper::toDTO).toList();
    }

    @Override
    public SubjectDTO findByName(String name) throws BadRequestException {
        return this.subjectRepository.findByName(name).map(this.subjectMapper::toDTO).orElseThrow(BadRequestException::new);
    }


    public Map<Long,SubjectMapDTO> findAll(){
        return subjectRepository.findAll().stream()
                .collect(Collectors.toMap(
                        Subject::getId,
                        materia -> new SubjectMapDTO(
                                materia.getName(),
                                // horarios -> stream directo
                                materia.getSchedule().stream()
                                        .map(h -> new ScheduleMapDTO(
                                                h.getDay(),
                                                h.getStartTime() + "-" + h.getEndTime()
                                        ))
                                        .toList(),
                                // aula -> tomo del primero o null
                                materia.getSchedule().stream()
                                        .findFirst()
                                        .map(Schedule::getClassroom)
                                        .orElse(null),
                                // profesor -> tomo el primero o null
                                materia.getProfessors().stream()
                                        .findFirst()
                                        .map(Professor::getName)
                                        .orElse(null)
                        )
                ));
    }
}
