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
public class SubjectMapDTO {
    private Long id;
    private String name;
    private List<commissionMapDTO> commissions;
}
