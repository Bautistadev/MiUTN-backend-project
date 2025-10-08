package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.contract;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.CareerRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.CareerDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;

import java.util.List;
import java.util.Map;

public interface CareerServiceContract {
    public void save(CareerRequestDTO careerRequestDTO);
    public void update(CareerDTO careerDTO) throws BadRequestException;
    public void delete(Long id) throws BadRequestException;
    public Boolean existsById(Long id);
    public Boolean existsByName(String name);
    public CareerDTO findById(Long id) throws BadRequestException;
    public CareerDTO findByName(String name) throws BadRequestException;
    public List<CareerDTO> findAll(Integer from, Integer to);
    public Map<Long, String> findAll();
}
