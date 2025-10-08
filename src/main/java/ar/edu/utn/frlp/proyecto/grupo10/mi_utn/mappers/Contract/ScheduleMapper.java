package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.ScheduleRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.ScheduleDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Schedule;

public interface ScheduleMapper {
    public Schedule toEntity(ScheduleDTO scheduleDTO);
    public Schedule toEntity(ScheduleRequestDTO scheduleRequestDTO);
    public ScheduleDTO toDTO(Schedule schedule);
}
