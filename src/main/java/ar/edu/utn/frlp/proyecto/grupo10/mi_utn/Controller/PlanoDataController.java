package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Controller;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.PlanoDataDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.PlanoDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/planos")
public class PlanoDataController {

    private final PlanoDataService planoDataService;

    public PlanoDataController(PlanoDataService planoDataService) {
        this.planoDataService = planoDataService;
    }

    @GetMapping("/{planoId}/datos")
    public ResponseEntity<PlanoDataDTO> obtenerDatosPlano(@PathVariable String planoId) {
        try {
            PlanoDataDTO datos = planoDataService.obtenerDatosPlano(planoId);
            return ResponseEntity.ok(datos);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{planoId}/datos")
    public ResponseEntity<PlanoDataDTO> guardarDatosPlano(
            @PathVariable String planoId,
            @RequestBody PlanoDataDTO planoData) {
        try {
            planoDataService.guardarDatosPlano(planoId, planoData);
            return ResponseEntity.ok(planoData);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}