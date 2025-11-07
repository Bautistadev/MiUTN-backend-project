package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.PasilloDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.PasilloMapper;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Career;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Floor;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Nodo;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Pasillo;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.CareerRepository;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.FloorRepository;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.NodoRepository;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.PasilloRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// PasilloService.java
@Service
@Transactional
public class PasilloService {

    private final PasilloRepository pasilloRepository;
    private final NodoRepository nodoRepository;
    private final CareerRepository careerRepository;
    private final FloorRepository floorRepository;
    private final PasilloMapper pasilloMapper;

    public PasilloService(PasilloRepository pasilloRepository, NodoRepository nodoRepository,
                          CareerRepository careerRepository, FloorRepository floorRepository,
                          PasilloMapper pasilloMapper) {
        this.pasilloRepository = pasilloRepository;
        this.nodoRepository = nodoRepository;
        this.careerRepository = careerRepository;
        this.floorRepository = floorRepository;
        this.pasilloMapper = pasilloMapper;
    }

    public PasilloDTO crearPasillo(PasilloDTO pasilloDTO) {
        // Validar nodos
        Nodo desde = nodoRepository.findById(pasilloDTO.getDesdeNodoId())
                .orElseThrow(() -> new RuntimeException("Nodo 'desde' no encontrado: " + pasilloDTO.getDesdeNodoId()));

        Nodo hasta = nodoRepository.findById(pasilloDTO.getHastaNodoId())
                .orElseThrow(() -> new RuntimeException("Nodo 'hasta' no encontrado: " + pasilloDTO.getHastaNodoId()));

        // Validar career y floor
        Career career = careerRepository.findById(pasilloDTO.getCareerId())
                .orElseThrow(() -> new RuntimeException("Career no encontrada: " + pasilloDTO.getCareerId()));

        Floor floor = floorRepository.findById(pasilloDTO.getFloorId())
                .orElseThrow(() -> new RuntimeException("Floor no encontrado: " + pasilloDTO.getFloorId()));

        Pasillo pasillo = pasilloMapper.toEntity(pasilloDTO, desde, hasta, career, floor);
        pasillo = pasilloRepository.save(pasillo);

        return pasilloMapper.toDto(pasillo);
    }

    public void eliminarPasillo(Long id) {
        Pasillo pasillo = pasilloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pasillo no encontrado: " + id));

        pasilloRepository.delete(pasillo);
    }

    public List<PasilloDTO> obtenerPasillosPorPlano(String planoId) {
        return pasilloRepository.findByPlanoId(planoId)
                .stream()
                .map(pasilloMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<PasilloDTO> obtenerPasillosPorNodo(Long nodoId) {
        return pasilloRepository.findByDesdeIdOrHastaId(nodoId, nodoId)
                .stream()
                .map(pasilloMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<PasilloDTO> obtenerTodosPasillos() {
        return pasilloRepository.findAll()
                .stream()
                .map(pasilloMapper::toDto)
                .collect(Collectors.toList());
    }
}