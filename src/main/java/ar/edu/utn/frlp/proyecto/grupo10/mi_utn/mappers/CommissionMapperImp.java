package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.CommissionRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.CommissionDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract.CommissionMapper;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Commission;
import org.springframework.stereotype.Component;

@Component
public class CommissionMapperImp implements CommissionMapper {
    @Override
    public Commission toEntity(CommissionRequestDTO commissionRequestDTO) {
        return Commission.builder()
                .name(commissionRequestDTO.getName())
                .build();
    }

    @Override
    public Commission toEntity(CommissionDTO commissionDTO) {
        return Commission.builder()
                .name(commissionDTO.getName())
                .id(commissionDTO.getId())
                .build();
    }

    @Override
    public CommissionDTO toDTO(Commission commission) {
        return CommissionDTO.builder()
                .id(commission.getId())
                .name(commission.getName())
                .date(commission.getDate())
                .dateUpdate(commission.getDateUpdate())
                .dateDeleted(commission.getDateDeleted())
                .build();
    }
}
