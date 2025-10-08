package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {
    private Long id;// FK en schedules
    private CommissionDTO commission;
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDateTime date;
    private LocalDateTime dateUpdate;
    private LocalDateTime dateDeleted;
}
