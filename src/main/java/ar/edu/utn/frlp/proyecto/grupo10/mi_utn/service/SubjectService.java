package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.SubjectRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.ScheduleMapDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.SubjectDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.SubjectMapDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.commissionMapDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.ConflictException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract.SubjectMapper;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Commission;
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

    public List<SubjectMapDTO> findAll() {
        return subjectRepository.findAll().stream()
                .map(this::mapToSubjectDTO)
                .toList();
    }

    // -------------------------
    // Métodos privados de mapeo
    // -------------------------

    private SubjectMapDTO mapToSubjectDTO(Subject subject) {
        return SubjectMapDTO.builder()
                .id(subject.getId())
                .name(subject.getName())
                .commissions(buildCommissions(subject.getSchedule()))
                .build();
    }

    private List<commissionMapDTO> buildCommissions(List<Schedule> schedules) {
        return schedules.stream()
                .collect(Collectors.groupingBy(Schedule::getCommission))
                .entrySet().stream()
                .map(entry -> mapToCommissionDTO(entry.getKey(), entry.getValue()))
                .toList();
    }

    private commissionMapDTO mapToCommissionDTO(Commission commission, List<Schedule> schedules) {
        return commissionMapDTO.builder()
                .commission(commission.getName())
                .classroom(extractAula(schedules))
                .professor(extractProfesor(schedules))
                .dates(buildSchedules(schedules))
                .build();
    }

    private String extractAula(List<Schedule> schedules) {
        return schedules.stream()
                .map(Schedule::getClassroom)
                .findFirst()
                .orElse("Sin Aula");
    }

    private String extractProfesor(List<Schedule> schedules) {
        return schedules.stream()
                .map(s -> s.getProfessor().getName() + " " + s.getProfessor().getLastname())
                .findFirst()
                .orElse("Sin Profesor");
    }

    private List<ScheduleMapDTO> buildSchedules(List<Schedule> schedules) {
        return schedules.stream()
                .map(s -> ScheduleMapDTO.builder()
                        .day(s.getDay())
                        .time(s.getStartTime() + "-" + s.getEndTime())
                        .classroom(s.getClassroom())
                        .build())
                .toList();
    }
}
