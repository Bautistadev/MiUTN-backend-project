package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response;


import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Career;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Professor;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Schedule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    private CommissionDTO commission;
    private Integer year;
    private String type;
    private List<ScheduleDTO> schedule;
    private CareerDTO career;
    private Set<ProfessorDTO> professors;
    private LocalDateTime date;
    private LocalDateTime dateUpdate;
    private LocalDateTime dateDeleted;

}
