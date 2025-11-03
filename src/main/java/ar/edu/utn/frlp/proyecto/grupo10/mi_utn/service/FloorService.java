package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.FloorDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.FloorMapper;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Floor;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.FloorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// FloorService.java
@Service
@Transactional
public class FloorService {

    private final FloorRepository floorRepository;
    private final FloorMapper floorMapper;

    public FloorService(FloorRepository floorRepository, FloorMapper floorMapper) {
        this.floorRepository = floorRepository;
        this.floorMapper = floorMapper;
    }

    public List<FloorDTO> obtenerTodosFloors() {
        return floorRepository.findAllByOrderByNameAsc()
                .stream()
                .map(floorMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<FloorDTO> obtenerFloorPorId(Long id) {
        return floorRepository.findById(id)
                .map(floorMapper::toDto);
    }

    public FloorDTO crearFloor(FloorDTO floorDTO) {
        Floor floor = floorMapper.toEntity(floorDTO);
        Floor floorGuardado = floorRepository.save(floor);
        return floorMapper.toDto(floorGuardado);
    }

    public void eliminarFloor(Long id) {
        Floor floor = floorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Floor no encontrado: " + id));
        floorRepository.delete(floor);
    }
}