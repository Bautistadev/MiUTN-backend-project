package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Controller;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.NodoDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.NodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// NodoController.java
@RestController
@RequestMapping("/api/nodos")
public class NodoController {

    private final NodoService nodoService;

    public NodoController(NodoService nodoService) {
        this.nodoService = nodoService;
    }

    @PostMapping
    public NodoDTO crearNodo(@RequestBody NodoDTO nodoDTO) {
        return nodoService.guardarNodo(nodoDTO);
    }

    @PutMapping("/{id}")
    public NodoDTO actualizarNodo(@PathVariable Long id, @RequestBody NodoDTO nodoDTO) {
        nodoDTO.setId(id);
        return nodoService.guardarNodo(nodoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNodo(@PathVariable Long id) {
        try {
            nodoService.eliminarNodo(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/plano/{planoId}")
    public List<NodoDTO> obtenerNodosPorPlano(@PathVariable String planoId) {
        return nodoService.obtenerNodosPorPlano(planoId);
    }

    @GetMapping
    public List<NodoDTO> obtenerTodosNodos() {
        return nodoService.obtenerTodosNodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NodoDTO> obtenerNodoPorId(@PathVariable Long id) {
        return nodoService.obtenerNodoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}