package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.ProfessorDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Enum.Role;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Professor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserRequestDTO {
    private String username;
    private String password;
    private Role role;
    private ProfessorDTO person;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdate;
    private LocalDateTime dateDeleted;
}
