package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorRequestDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String lastname;
}
