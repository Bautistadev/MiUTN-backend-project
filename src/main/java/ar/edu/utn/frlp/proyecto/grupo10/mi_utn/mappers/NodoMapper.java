package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.mappers;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.NodoDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NodoMapper {

    public NodoDTO toDto(Nodo nodo) {
        if (nodo == null) {
            return null;
        }

        NodoDTO dto = new NodoDTO();
        dto.setId(nodo.getId());
        dto.setNombre(nodo.getNombre());
        dto.setTipo(nodo.getTipo());
        dto.setPlanoId(nodo.getPlanoId());
        dto.setCareerId(nodo.getCareer().getId());
        dto.setFloorId(nodo.getFloor().getId());
        dto.setCoordenadaX(nodo.getCoordenadaX());
        dto.setCoordenadaY(nodo.getCoordenadaY());

        // Si es área, mapear puntos
        if (nodo instanceof Area area) {
            dto.setPuntos(area.getPuntos());
        }

        // Si es escalera, mapear campos específicos (NUEVA VERSIÓN)
        if (nodo instanceof Escalera escalera) {
            dto.setCarreraActualId(escalera.getCarreraActual().getId());
            dto.setPisoActualId(escalera.getPisoActual().getId());
            dto.setDireccion(escalera.getDireccion());

            // 🔥 Mapear múltiples destinos
            List<Long> carrerasDestinoIds = escalera.getDestinos().stream()
                    .map(destino -> destino.getCarreraDestino().getId())
                    .collect(Collectors.toList());
            dto.setCarrerasDestinoIds(carrerasDestinoIds);

            List<Long> pisosDestinoIds = escalera.getDestinos().stream()
                    .map(destino -> destino.getPisoDestino().getId())
                    .collect(Collectors.toList());
            dto.setPisosDestinoIds(pisosDestinoIds);

            // 🔥 Mapear múltiples conexiones
            List<Long> escalerasConectadasIds = escalera.getConexiones().stream()
                    .map(conexion -> conexion.getEscaleraDestino().getId())
                    .collect(Collectors.toList());
            dto.setEscalerasConectadasIds(escalerasConectadasIds);
        }

        return dto;
    }

    public Nodo toEntity(NodoDTO dto, Career career, Floor floor) {
        if (dto == null) {
            return null;
        }

        Nodo nodo;

        // Crear la entidad específica según el tipo
        if (dto.getTipo().esArea()) {
            Area area = new Area();
            area.setPuntos(dto.getPuntos() != null ? dto.getPuntos() : new ArrayList<>());
            nodo = area;
        } else {
            nodo = new PuntoEspecial();
        }

        // Campos comunes
        nodo.setId(dto.getId());
        nodo.setNombre(dto.getNombre());
        nodo.setTipo(dto.getTipo());
        nodo.setPlanoId(dto.getPlanoId());
        nodo.setCareer(career);
        nodo.setFloor(floor);
        nodo.setCoordenadaX(dto.getCoordenadaX());
        nodo.setCoordenadaY(dto.getCoordenadaY());

        return nodo;
    }

    // 🔥 NUEVO MÉTODO PARA ESCALERA CON MÚLTIPLES DESTINOS
    public Escalera toEscaleraEntity(NodoDTO dto,
                                     Career carreraActual,
                                     Floor pisoActual,
                                     List<Career> carrerasDestino,
                                     List<Floor> pisosDestino,
                                     List<Escalera> escalerasConectadas) {
        if (dto == null) {
            return null;
        }

        Escalera escalera = new Escalera();

        // Campos de Nodo
        escalera.setId(dto.getId());
        escalera.setNombre(dto.getNombre());
        escalera.setTipo(dto.getTipo());
        escalera.setPlanoId(dto.getPlanoId());
        escalera.setCareer(carreraActual); // Usar carreraActual como career principal
        escalera.setFloor(pisoActual);     // Usar pisoActual como floor principal
        escalera.setCoordenadaX(dto.getCoordenadaX());
        escalera.setCoordenadaY(dto.getCoordenadaY());
        escalera.setPuntos(dto.getPuntos() != null ? dto.getPuntos() : new ArrayList<>());

        // Campos específicos de Escalera
        escalera.setCarreraActual(carreraActual);
        escalera.setPisoActual(pisoActual);
        escalera.setDireccion(dto.getDireccion());

        // 🔥 INICIALIZAR COLECCIONES
        escalera.setDestinos(new ArrayList<>());
        escalera.setConexiones(new ArrayList<>());

        // 🔥 AGREGAR MÚLTIPLES DESTINOS
        if (carrerasDestino != null && pisosDestino != null &&
                carrerasDestino.size() == pisosDestino.size()) {

            for (int i = 0; i < carrerasDestino.size(); i++) {
                DestinoEscalera destino = new DestinoEscalera();
                destino.setEscalera(escalera);
                destino.setCarreraDestino(carrerasDestino.get(i));
                destino.setPisoDestino(pisosDestino.get(i));
                destino.setOrden(i); // Orden según la posición en la lista
                escalera.getDestinos().add(destino);
            }
        }

        // 🔥 AGREGAR MÚLTIPLES CONEXIONES
        if (escalerasConectadas != null) {
            for (Escalera escaleraConectada : escalerasConectadas) {
                ConexionEscalera conexion = new ConexionEscalera();
                conexion.setEscaleraOrigen(escalera);
                conexion.setEscaleraDestino(escaleraConectada);
                escalera.getConexiones().add(conexion);
            }
        }

        return escalera;
    }

    // 🔥 MÉTODO PARA ACTUALIZAR ESCALERA EXISTENTE
    public void updateEscaleraFromDto(NodoDTO dto, Escalera escalera,
                                      List<Career> carrerasDestino,
                                      List<Floor> pisosDestino,
                                      List<Escalera> escalerasConectadas) {
        // Campos comunes de Nodo
        escalera.setNombre(dto.getNombre());
        escalera.setTipo(dto.getTipo());
        escalera.setPlanoId(dto.getPlanoId());
        escalera.setCoordenadaX(dto.getCoordenadaX());
        escalera.setCoordenadaY(dto.getCoordenadaY());
        escalera.setPuntos(dto.getPuntos() != null ? dto.getPuntos() : new ArrayList<>());

        // Campos específicos
        escalera.setDireccion(dto.getDireccion());

        // 🔥 ACTUALIZAR DESTINOS (limpiar y recrear)
        escalera.getDestinos().clear();
        if (carrerasDestino != null && pisosDestino != null &&
                carrerasDestino.size() == pisosDestino.size()) {

            for (int i = 0; i < carrerasDestino.size(); i++) {
                DestinoEscalera destino = new DestinoEscalera();
                destino.setEscalera(escalera);
                destino.setCarreraDestino(carrerasDestino.get(i));
                destino.setPisoDestino(pisosDestino.get(i));
                destino.setOrden(i);
                escalera.getDestinos().add(destino);
            }
        }

        // 🔥 ACTUALIZAR CONEXIONES (limpiar y recrear)
        escalera.getConexiones().clear();
        if (escalerasConectadas != null) {
            for (Escalera escaleraConectada : escalerasConectadas) {
                ConexionEscalera conexion = new ConexionEscalera();
                conexion.setEscaleraOrigen(escalera);
                conexion.setEscaleraDestino(escaleraConectada);
                escalera.getConexiones().add(conexion);
            }
        }
    }
}