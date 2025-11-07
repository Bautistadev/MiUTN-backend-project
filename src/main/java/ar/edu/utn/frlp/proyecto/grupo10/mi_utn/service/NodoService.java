package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.NodoDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers.NodoMapper;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Career;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Enum.TipoNodo;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Escalera;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Floor;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Nodo;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.CareerRepository;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.EscaleraRepository;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.FloorRepository;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.NodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@Transactional
public class NodoService {

    private final NodoRepository nodoRepository;
    private final CareerRepository careerRepository;
    private final FloorRepository floorRepository;
    private final EscaleraRepository escaleraRepository;
    private final NodoMapper nodoMapper;

    public NodoService(NodoRepository nodoRepository, CareerRepository careerRepository,
                       FloorRepository floorRepository, EscaleraRepository escaleraRepository,
                       NodoMapper nodoMapper) {
        this.nodoRepository = nodoRepository;
        this.careerRepository = careerRepository;
        this.floorRepository = floorRepository;
        this.escaleraRepository = escaleraRepository;
        this.nodoMapper = nodoMapper;
    }

    public NodoDTO guardarNodo(NodoDTO nodoDTO) {
        // Validar career y floor
        Career career = careerRepository.findById(nodoDTO.getCareerId())
                .orElseThrow(() -> new RuntimeException("Career no encontrada: " + nodoDTO.getCareerId()));

        Floor floor = floorRepository.findById(nodoDTO.getFloorId())
                .orElseThrow(() -> new RuntimeException("Floor no encontrado: " + nodoDTO.getFloorId()));

        Nodo nodo;

        // Manejar escalera de forma especial
        if (nodoDTO.getTipo() == TipoNodo.ESCALERA) {
            nodo = guardarEscalera(nodoDTO, career, floor);
        } else {
            nodo = nodoMapper.toEntity(nodoDTO, career, floor);
            nodo = nodoRepository.save(nodo);
        }

        return nodoMapper.toDto(nodo);
    }

    private Escalera guardarEscalera(NodoDTO dto, Career career, Floor floor) {
        // 🔥 VALIDAR CARRERA Y PISO ACTUAL
        Career carreraActual = careerRepository.findById(dto.getCarreraActualId())
                .orElseThrow(() -> new RuntimeException("Career actual no encontrada: " + dto.getCarreraActualId()));

        Floor pisoActual = floorRepository.findById(dto.getPisoActualId())
                .orElseThrow(() -> new RuntimeException("Piso actual no encontrado: " + dto.getPisoActualId()));

        // 🔥 VALIDAR MÚLTIPLES CARRERAS DESTINO
        List<Career> carrerasDestino = new ArrayList<>();
        if (dto.getCarrerasDestinoIds() != null && !dto.getCarrerasDestinoIds().isEmpty()) {
            carrerasDestino = careerRepository.findAllById(dto.getCarrerasDestinoIds());
            if (carrerasDestino.size() != dto.getCarrerasDestinoIds().size()) {
                throw new RuntimeException("Algunas careers destino no fueron encontradas");
            }
        }

        // 🔥 VALIDAR MÚLTIPLES PISOS DESTINO
        List<Floor> pisosDestino = new ArrayList<>();
        if (dto.getPisosDestinoIds() != null && !dto.getPisosDestinoIds().isEmpty()) {
            pisosDestino = floorRepository.findAllById(dto.getPisosDestinoIds());
            if (pisosDestino.size() != dto.getPisosDestinoIds().size()) {
                throw new RuntimeException("Algunos pisos destino no fueron encontrados");
            }
        }

        // 🔥 VALIDAR QUE HAYA LA MISMA CANTIDAD DE CARRERAS Y PISOS DESTINO
        if (carrerasDestino.size() != pisosDestino.size()) {
            throw new RuntimeException("La cantidad de careers destino debe coincidir con la cantidad de pisos destino");
        }

        // 🔥 VALIDAR MÚLTIPLES ESCALERAS CONECTADAS
        List<Escalera> escalerasConectadas = new ArrayList<>();
        if (dto.getEscalerasConectadasIds() != null && !dto.getEscalerasConectadasIds().isEmpty()) {
            escalerasConectadas = escaleraRepository.findAllById(dto.getEscalerasConectadasIds());
            if (escalerasConectadas.size() != dto.getEscalerasConectadasIds().size()) {
                throw new RuntimeException("Algunas escaleras conectadas no fueron encontradas");
            }
        }

        // 🔥 CREAR ESCALERA CON MÚLTIPLES DESTINOS Y CONEXIONES
        Escalera escalera = nodoMapper.toEscaleraEntity(dto, carreraActual, pisoActual,
                carrerasDestino, pisosDestino, escalerasConectadas);

        return escaleraRepository.save(escalera);
    }

    // 🔥 MÉTODO PARA ACTUALIZAR ESCALERA
    public NodoDTO actualizarNodo(Long id, NodoDTO nodoDTO) {
        Nodo nodoExistente = nodoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nodo no encontrado: " + id));

        if (nodoExistente instanceof Escalera && nodoDTO.getTipo() == TipoNodo.ESCALERA) {
            return actualizarEscalera((Escalera) nodoExistente, nodoDTO);
        } else {
            // Actualizar nodo normal
            Career career = careerRepository.findById(nodoDTO.getCareerId())
                    .orElseThrow(() -> new RuntimeException("Career no encontrada: " + nodoDTO.getCareerId()));

            Floor floor = floorRepository.findById(nodoDTO.getFloorId())
                    .orElseThrow(() -> new RuntimeException("Floor no encontrado: " + nodoDTO.getFloorId()));

            Nodo nodoActualizado = nodoMapper.toEntity(nodoDTO, career, floor);
            nodoActualizado.setId(id);
            nodoActualizado = nodoRepository.save(nodoActualizado);

            return nodoMapper.toDto(nodoActualizado);
        }
    }

    private NodoDTO actualizarEscalera(Escalera escaleraExistente, NodoDTO dto) {
        // Validar carrera y piso actual
        Career carreraActual = careerRepository.findById(dto.getCarreraActualId())
                .orElseThrow(() -> new RuntimeException("Career actual no encontrada: " + dto.getCarreraActualId()));

        Floor pisoActual = floorRepository.findById(dto.getPisoActualId())
                .orElseThrow(() -> new RuntimeException("Piso actual no encontrado: " + dto.getPisoActualId()));

        // Validar múltiples careers destino
        List<Career> carrerasDestino = new ArrayList<>();
        if (dto.getCarrerasDestinoIds() != null && !dto.getCarrerasDestinoIds().isEmpty()) {
            carrerasDestino = careerRepository.findAllById(dto.getCarrerasDestinoIds());
            if (carrerasDestino.size() != dto.getCarrerasDestinoIds().size()) {
                throw new RuntimeException("Algunas careers destino no fueron encontradas");
            }
        }

        // Validar múltiples pisos destino
        List<Floor> pisosDestino = new ArrayList<>();
        if (dto.getPisosDestinoIds() != null && !dto.getPisosDestinoIds().isEmpty()) {
            pisosDestino = floorRepository.findAllById(dto.getPisosDestinoIds());
            if (pisosDestino.size() != dto.getPisosDestinoIds().size()) {
                throw new RuntimeException("Algunos pisos destino no fueron encontrados");
            }
        }

        // Validar que haya la misma cantidad de careers y pisos destino
        if (carrerasDestino.size() != pisosDestino.size()) {
            throw new RuntimeException("La cantidad de careers destino debe coincidir con la cantidad de pisos destino");
        }

        // Validar múltiples escaleras conectadas
        List<Escalera> escalerasConectadas = new ArrayList<>();
        if (dto.getEscalerasConectadasIds() != null && !dto.getEscalerasConectadasIds().isEmpty()) {
            escalerasConectadas = escaleraRepository.findAllById(dto.getEscalerasConectadasIds());
            if (escalerasConectadas.size() != dto.getEscalerasConectadasIds().size()) {
                throw new RuntimeException("Algunas escaleras conectadas no fueron encontradas");
            }
        }

        // 🔥 ACTUALIZAR ESCALERA EXISTENTE
        nodoMapper.updateEscaleraFromDto(dto, escaleraExistente, carrerasDestino, pisosDestino, escalerasConectadas);

        // Actualizar career y floor principales
        escaleraExistente.setCareer(carreraActual);
        escaleraExistente.setFloor(pisoActual);

        Escalera escaleraActualizada = escaleraRepository.save(escaleraExistente);
        return nodoMapper.toDto(escaleraActualizada);
    }

    public void eliminarNodo(Long id) {
        Nodo nodo = nodoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nodo no encontrado: " + id));

        nodoRepository.deleteById(id);
    }

    public List<NodoDTO> obtenerNodosPorPlano(String planoId) {
        return nodoRepository.findByPlanoId(planoId)
                .stream()
                .map(nodoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<NodoDTO> obtenerTodosNodos() {
        return nodoRepository.findAll()
                .stream()
                .map(nodoMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<NodoDTO> obtenerNodoPorId(Long id) {
        return nodoRepository.findById(id)
                .map(nodoMapper::toDto);
    }

    // 🔥 MÉTODO ESPECÍFICO PARA OBTENER ESCALERAS POR PISO
    public List<Escalera> obtenerEscalerasPorPiso(Long pisoId) {
        return escaleraRepository.findByPisoActualId(pisoId);
    }

    // 🔥 MÉTODO PARA OBTENER ESCALERAS QUE CONECTAN CON UN PISO DESTINO
    public List<Escalera> obtenerEscalerasPorDestinoCompleto(Long carreraDestinoId, Long pisoDestinoId) {
        return escaleraRepository.findEscalerasByDestinoCompleto(carreraDestinoId, pisoDestinoId);
    }
}