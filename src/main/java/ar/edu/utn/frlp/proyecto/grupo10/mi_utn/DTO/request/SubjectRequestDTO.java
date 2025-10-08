package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Career;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Professor;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Schedule;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Set;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectRequestDTO {

    @NotBlank
    private String name;
    @NotBlank
    private Long commissionId;
    @NotBlank
    private Integer year;
    @NotBlank
    private String type;
    @NotBlank
    private List<ScheduleRequestDTO> schedule;
    @NotBlank
    private Long careerId;
    @NotBlank
    private Set<Long> professorsId;

}
