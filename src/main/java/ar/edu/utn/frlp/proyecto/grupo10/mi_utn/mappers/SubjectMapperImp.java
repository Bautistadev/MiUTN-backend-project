package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.SubjectRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.*;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract.SubjectMapper;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SubjectMapperImp implements SubjectMapper {

    private CommissionMapperImp commissionMapperImp;


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

        List<Schedule> scheduleDTO = subjectRequestDTO.getSchedule()
                .stream()
                .map(e->Schedule.builder()
                        .day(e.getDay())
                        .startTime(e.getStartTime())
                        .endTime(e.getEndTime())
                        .build())
                .toList();

        return null;/*Subject.builder()
                .name(subjectRequestDTO.getName())
                .commission(Commission.builder().id(subjectRequestDTO.getCommissionId()).build())
                .year(subjectRequestDTO.getYear())
                .type(subjectRequestDTO.getType())
                .schedule(scheduleDTO)
                .build();*/
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
                        .commission(this.commissionMapperImp.toDTO(e.getCommission()))
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
