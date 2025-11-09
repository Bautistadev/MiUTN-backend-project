package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.CommissionRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.CommissionDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.ConflictException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract.CommissionMapper;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Commission;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.CommissionRespository;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.contract.CommissionServiceContract;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommissionService implements CommissionServiceContract {

    private CommissionRespository commissionRespository;
    private CommissionMapper commissionMapper;

    @Override
    public CommissionDTO save(CommissionRequestDTO commissionRequestDTO) {
        if(this.commissionRespository.existsByName(commissionRequestDTO.getName()))
            throw new ConflictException("Registro existente");

        Commission commission = this.commissionRespository.save(this.commissionMapper.toEntity(commissionRequestDTO));
        return commissionMapper.toDTO(commission);
    }

    @Override
    public void update(CommissionDTO commissionDTO) throws BadRequestException {
        if(!this.commissionRespository.existsById(commissionDTO.getId()))
            throw new BadRequestException("Registro no existente");

        this.commissionRespository.save(this.commissionMapper.toEntity(commissionDTO));
    }

    @Override
    public void delete(Long id) throws BadRequestException {
        if(!this.commissionRespository.existsById(id))
            throw new BadRequestException("Registro no existente");

        this.commissionRespository.deleteById(id);
    }

    @Override
    public List<CommissionDTO> findAll() {
        return this.commissionRespository.findAll().stream().map(this.commissionMapper::toDTO).toList();
    }

    @Override
    public CommissionDTO findById(Long id) throws BadRequestException {
        return this.commissionRespository.findById(id).map(this.commissionMapper::toDTO).orElseThrow(BadRequestException::new);
    }

    @Override
    public CommissionDTO findByName(String name) throws BadRequestException {
        return this.commissionRespository.findByName(name).map(this.commissionMapper::toDTO).orElseThrow(BadRequestException::new);
    }
}
