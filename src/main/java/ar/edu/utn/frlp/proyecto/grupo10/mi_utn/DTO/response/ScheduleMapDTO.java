package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ScheduleMapDTO {
    private String day;
    private String time;   // "08:00-10:00"
    private String classroom;
}
