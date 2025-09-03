package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.CareerRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.CareerDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.ConflictException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.Contract.CareerMapper;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Career;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.CareerRepository;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.contract.CareerServiceContract;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CareerService implements CareerServiceContract {

    private CareerRepository careerRepository;
    private CareerMapper careerMapper;

    @Override
    public void save(CareerRequestDTO careerRequestDTO) {
        if(this.careerRepository.existsByName( careerRequestDTO.getName()))
            throw new ConflictException("Registro existente");

        this.careerRepository.save(this.careerMapper.toEntity(careerRequestDTO));
    }

    @Override
    public void update(CareerDTO careerDTO) throws BadRequestException {
        if(!this.careerRepository.existsById(careerDTO.getId()))
            throw new BadRequestException("Registro no existente");

        this.careerRepository.save(this.careerMapper.toEntity(careerDTO));
    }

    @Override
    public void delete(Long id) throws BadRequestException {
        if(!this.careerRepository.existsById(id))
            throw new BadRequestException("Registro no existente");

        this.careerRepository.deleteById(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return this.careerRepository.existsById(id);
    }

    @Override
    public Boolean existsByName(String name) {
        return this.careerRepository.existsByName(name);
    }

    @Override
    public CareerDTO findById(Long id) throws BadRequestException {
        return this.careerRepository.findById(id).map(this.careerMapper::toDTO).orElseThrow(BadRequestException::new);
    }

    @Override
    public CareerDTO findByName(String name) throws BadRequestException {
        return this.careerRepository.findByName(name).map(this.careerMapper::toDTO).orElseThrow(BadRequestException::new);
    }

    @Override
    public List<CareerDTO> findAll(Integer from, Integer to) {
        if((from == null || from == 0) && (to == null || to == 0))
            return this.careerRepository.findAll().stream().map(careerMapper::toDTO).toList();
        if(from == null || from == 0){
            Pageable pageable = PageRequest.of(0,to);
            return this.careerRepository.findAll(pageable).stream().map(careerMapper::toDTO).toList();
        }
        Pageable pageable = PageRequest.of(from,to);
        return this.careerRepository.findAll(pageable).stream().map(careerMapper::toDTO).toList();
    }
}
