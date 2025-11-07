package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Controller;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.FloorDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.FloorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/floors")
public class FloorController {

    private final FloorService floorService;

    public FloorController(FloorService floorService) {
        this.floorService = floorService;
    }

    @GetMapping
    public List<FloorDTO> obtenerTodosFloors() {
        return floorService.obtenerTodosFloors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FloorDTO> obtenerFloorPorId(@PathVariable Long id) {
        return floorService.obtenerFloorPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FloorDTO crearFloor(@RequestBody FloorDTO floorDTO) {
        return floorService.crearFloor(floorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFloor(@PathVariable Long id) {
        try {
            floorService.eliminarFloor(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}