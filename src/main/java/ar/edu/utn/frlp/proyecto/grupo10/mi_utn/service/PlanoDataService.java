package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.NodoDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.PasilloDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.PlanoDataDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// PlanoDataService.java
@Service
@Transactional
public class PlanoDataService {

    private final NodoService nodoService;
    private final PasilloService pasilloService;

    public PlanoDataService(NodoService nodoService, PasilloService pasilloService) {
        this.nodoService = nodoService;
        this.pasilloService = pasilloService;
    }

    public PlanoDataDTO obtenerDatosPlano(String planoId) {
        List<NodoDTO> nodos = nodoService.obtenerNodosPorPlano(planoId);
        List<PasilloDTO> pasillos = pasilloService.obtenerPasillosPorPlano(planoId);

        PlanoDataDTO planoData = new PlanoDataDTO();
        planoData.setPlanoId(planoId);
        planoData.setNodos(nodos);
        planoData.setPasillos(pasillos);

        return planoData;
    }

    public void guardarDatosPlano(String planoId, PlanoDataDTO planoData) {
        // Guardar/actualizar nodos
        for (NodoDTO nodoDTO : planoData.getNodos()) {
            nodoDTO.setPlanoId(planoId);
            nodoService.guardarNodo(nodoDTO);
        }

        // Guardar/actualizar pasillos
        for (PasilloDTO pasilloDTO : planoData.getPasillos()) {
            pasilloDTO.setPlanoId(planoId);
            pasilloService.crearPasillo(pasilloDTO);
        }
    }
}