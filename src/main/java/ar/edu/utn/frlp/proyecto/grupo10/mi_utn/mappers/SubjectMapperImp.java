package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.SubjectRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.*;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract.SubjectMapper;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.*;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.CareerRepository;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.CommissionRespository;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.ProfessorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SubjectMapperImp implements SubjectMapper {

    private CommissionMapperImp commissionMapperImp;
    private ProfessorRepository professorRepository;
    private CareerRepository careerRepository;
    private CommissionRespository commissionRespository;


    @Override
    public Subject toEntity(SubjectDTO subjectDTO) {

        List<Schedule> schedules =  subjectDTO.getSchedule()
                .stream()
                .map(e->Schedule.builder().id(e.getId()).build())
                .toList();

        Set<Professor> professors =  subjectDTO.getProfessors()
                .stream().map(e-> Professor.builder().id(e.getId()).build())
                .collect(Collectors.toSet());

        /*return Subject.builder()
                .id(subjectDTO.getId())
                .name(subjectDTO.getName())
                .commission(commission)
                .year(subjectDTO.getYear())
                .type(subjectDTO.getType())
                .schedule(schedules)
                .career(Career.builder().id(subjectDTO.getId()).build())
                .professors(professors)
                .build();*/

        return null;
    }

    @Override
    public Subject toEntity(SubjectRequestDTO subjectRequestDTO) {



        Set<Professor> professors = subjectRequestDTO.getProfessorsId()
                .stream()
                .map(e-> this.professorRepository.getReferenceById(e)).collect(Collectors.toSet());

        Subject subject = Subject.builder()
                .name(subjectRequestDTO.getName())
                .type(subjectRequestDTO.getType())
                .career(this.careerRepository.getReferenceById(subjectRequestDTO.getCareerId()))
                .professors(professors)
                .date(LocalDateTime.now())
                .year(subjectRequestDTO.getYear())
                .build();

        List<Schedule> schedule = subjectRequestDTO.getSchedule()
                .stream()
                .map(e->Schedule.builder()
                        .day(e.getDay())
                        .startTime(e.getStartTime())
                        .endTime(e.getEndTime())
                        .classroom(e.getClassroom())
                        .date(LocalDateTime.now())
                        .subject(subject)
                        .professor(professors.stream().findFirst().orElse(null))
                        .commission(this.commissionRespository.getReferenceById(subjectRequestDTO.getCommissionId()))
                        .build())
                .toList();

        subject.setSchedule(schedule);
        return subject;
    }

    @Override
    public SubjectDTO toDTO(Subject subject) {
        List<ScheduleDTO> schedules = subject.getSchedule()
                .stream().map(e-> ScheduleDTO.builder()
                        .id(e.getId())
                        .day(e.getDay())
                        .commission(this.commissionMapperImp.toDTO(e.getCommission()))
                        .classroom(e.getClassroom())
                        .endTime(e.getEndTime())
                        .startTime(e.getStartTime())
                        .build())
                .toList();

        CareerDTO careerDTO = CareerDTO.builder()
                .id(subject.getCareer().getId())
                .name(subject.getCareer().getName())
                .build();

        Set<ProfessorDTO> professorDTOS = subject.getProfessors()
                .stream().map(e-> ProfessorDTO.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .lastname(e.getLastname())
                        .email(e.getEmail())
                        .build())
                .collect(Collectors.toSet());



        return SubjectDTO.builder()
                .id(subject.getId())
                .name(subject.getName())
                .year(subject.getYear())
                .type(subject.getType())
                .schedule(schedules)
                .career(careerDTO)
                .professors(professorDTOS)
                .build();
    }
}
