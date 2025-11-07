package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Controller;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.PasilloDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.PasilloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/pasillos")
public class PasilloController {

    private final PasilloService pasilloService;

    public PasilloController(PasilloService pasilloService) {
        this.pasilloService = pasilloService;
    }

    @PostMapping
    public PasilloDTO crearPasillo(@RequestBody PasilloDTO pasilloDTO) {
        return pasilloService.crearPasillo(pasilloDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPasillo(@PathVariable Long id) {
        try {
            pasilloService.eliminarPasillo(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/plano/{planoId}")
    public List<PasilloDTO> obtenerPasillosPorPlano(@PathVariable String planoId) {
        return pasilloService.obtenerPasillosPorPlano(planoId);
    }

    @GetMapping("/nodo/{nodoId}")
    public List<PasilloDTO> obtenerPasillosPorNodo(@PathVariable Long nodoId) {
        return pasilloService.obtenerPasillosPorNodo(nodoId);
    }

    @GetMapping
    public List<PasilloDTO> obtenerTodosPasillos() {
        return pasilloService.obtenerTodosPasillos();
    }
}