package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {
    private Long id;
    private String name;
    private String lastname;
    private String email;
}
