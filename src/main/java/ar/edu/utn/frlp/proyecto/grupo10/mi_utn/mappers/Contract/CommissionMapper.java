package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.CommissionRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.CommissionDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Commission;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.CommissionRespository;

public interface CommissionMapper {

    public Commission toEntity(CommissionRequestDTO commissionRequestDTO);
    public Commission toEntity(CommissionDTO commissionDTO);
    public CommissionDTO toDTO(Commission commission);
}
