package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class commissionMapDTO {
    private String commission;
    private String classroom;
    private String professor;
    private String email;
    private List<ScheduleMapDTO> dates;
}
