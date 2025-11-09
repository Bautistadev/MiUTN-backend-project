package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.contract;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.CommissionRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.CommissionDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Commission;

import java.util.List;

public interface CommissionServiceContract {
    public CommissionDTO save(CommissionRequestDTO commissionRequestDTO);
    public void update(CommissionDTO commissionDTO) throws BadRequestException;
    public void delete(Long id) throws BadRequestException;
    public List<CommissionDTO> findAll();
    public CommissionDTO findById(Long id) throws BadRequestException;
    public CommissionDTO findByName(String name) throws BadRequestException;
}
