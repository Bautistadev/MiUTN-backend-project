package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.ScheduleRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.ScheduleDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract.ScheduleMapper;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Commission;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Schedule;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapperImp implements ScheduleMapper {
    @Override
    public Schedule toEntity(ScheduleDTO scheduleDTO) {
        return Schedule.builder()
                .id(scheduleDTO.getId())
                .day(scheduleDTO.getDay())
                .startTime(scheduleDTO.getStartTime())
                .endTime(scheduleDTO.getEndTime())
                .commission(Commission.builder().id(scheduleDTO.getId()).build())
                .build();
    }

    @Override
    public Schedule toEntity(ScheduleRequestDTO scheduleRequestDTO) {
        return Schedule.builder()
                .day(scheduleRequestDTO.getDay())
                .startTime(scheduleRequestDTO.getStartTime())
                .endTime(scheduleRequestDTO.getEndTime())
                .build();
    }

    @Override
    public ScheduleDTO toDTO(Schedule schedule) {
        return ScheduleDTO.builder()
                .id(schedule.getId())
                .day(schedule.getDay())
                .endTime(schedule.getEndTime())
                .startTime(schedule.getStartTime())
                .date(schedule.getDate())
                .dateUpdate(schedule.getDateUpdate())
                .dateDeleted(schedule.getDateDeleted())
                .build();
    }
}
