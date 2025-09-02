package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.SubjectRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.CareerDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.ProfessorDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.ScheduleDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.SubjectDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract.SubjectMapper;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Career;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Professor;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Schedule;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Subject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SubjectMapperImp implements SubjectMapper {
    @Override
    public Subject toEntity(SubjectDTO subjectDTO) {

        List<Schedule> schedules =  subjectDTO.getSchedule()
                .stream()
                .map(e->Schedule.builder().id(e.getId()).build())
                .toList();

        Set<Professor> professors =  subjectDTO.getProfessors()
                .stream().map(e-> Professor.builder().id(e.getId()).build())
                .collect(Collectors.toSet());

        return Subject.builder()
                .id(subjectDTO.getId())
                .name(subjectDTO.getName())
                .commission(subjectDTO.getCommission())
                .year(subjectDTO.getYear())
                .type(subjectDTO.getType())
                .schedule(schedules)
                .career(Career.builder().id(subjectDTO.getId()).build())
                .professors(professors)
                .build();
    }

    @Override
    public Subject toEntity(SubjectRequestDTO subjectRequestDTO) {

        List<Schedule> scheduleDTO = subjectRequestDTO.getSchedule()
                .stream()
                .map(e->Schedule.builder()
                        .day(e.getDay())
                        .startTime(e.getStartTime())
                        .endTime(e.getEndTime())
                        .build())
                .toList();

        return Subject.builder()
                .name(subjectRequestDTO.getName())
                .commission(subjectRequestDTO.getCommission())
                .year(subjectRequestDTO.getYear())
                .type(subjectRequestDTO.getType())
                .schedule(scheduleDTO)
                .build();
    }

    @Override
    public SubjectDTO toDTO(Subject subject) {
        List<ScheduleDTO> schedules = subject.getSchedule()
                .stream().map(e-> ScheduleDTO.builder()
                        .id(e.getId())
                        .date(e.getDate())
                        .dateUpdate(e.getDateUpdate())
                        .dateDeleted(e.getDateDeleted())
                        .day(e.getDay())
                        .endTime(e.getEndTime())
                        .startTime(e.getStartTime())
                        .build())
                .toList();

        CareerDTO careerDTO = CareerDTO.builder()
                .id(subject.getCareer().getId())
                .name(subject.getCareer().getName())
                .date(subject.getCareer().getDate())
                .dateDeleted(subject.getCareer().getDateDeleted())
                .dateUpdate(subject.getCareer().getDateUpdate())
                .build();

        Set<ProfessorDTO> professorDTOS = subject.getProfessors()
                .stream().map(e-> ProfessorDTO.builder()
                        .id(e.getId())
                        .name(e.getName())
                        .lastname(e.getLastname())
                        .date(e.getDate())
                        .dateUpdate(e.getDateUpdate())
                        .dateDeleted(e.getDateDeleted())
                        .build())
                .collect(Collectors.toSet());

        return SubjectDTO.builder()
                .id(subject.getId())
                .name(subject.getName())
                .commission(subject.getCommission())
                .year(subject.getYear())
                .type(subject.getType())
                .schedule(schedules)
                .career(careerDTO)
                .professors(professorDTOS)
                .build();
    }
}
