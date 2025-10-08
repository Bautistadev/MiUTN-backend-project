package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {
    private Long id;
    private String name;
    private String lastname;
    private LocalDateTime date;
    private LocalDateTime dateUpdate;
    private LocalDateTime dateDeleted;
}
