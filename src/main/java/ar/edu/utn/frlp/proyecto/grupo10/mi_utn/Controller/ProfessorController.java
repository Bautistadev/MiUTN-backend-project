package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Controller;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.ProfessorDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/MiUTN")
@AllArgsConstructor
public class ProfessorController {

    private ProfessorService professorService;

    @GetMapping("findAll")
    public ResponseEntity<List<ProfessorDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.professorService.findAll());
    }
}
