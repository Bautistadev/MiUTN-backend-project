package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    private Long id;
    private String name;
    private Integer year;
    private String type;
    private List<ScheduleDTO> schedule;
    private CareerDTO career;
    private Set<ProfessorDTO> professors;

}
