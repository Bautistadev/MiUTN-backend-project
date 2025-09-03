package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Controller;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.ProfessorRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.ProfessorDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.ProfessorService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1//professor")
@AllArgsConstructor
public class ProfessorController {

    private ProfessorService professorService;

    @PostMapping("save")
    public ResponseEntity<Void>save(@RequestBody(required = true) @Valid ProfessorRequestDTO professorRequestDTO){
        this.professorService.save(professorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("update")
    public ResponseEntity<Void> update(@RequestBody(required = true) @Valid ProfessorDTO professorDTO) throws BadRequestException {
        this.professorService.update(professorDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> delete(@RequestParam(required = true) Long id) throws BadRequestException {
        this.professorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("findAll")
    public ResponseEntity<List<ProfessorDTO>> findAll( @RequestParam(required = false) Integer from,
                                                       @RequestParam(required = false) Integer to){
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.professorService.findAll(from,to));
    }

    @GetMapping("findById")
    public ResponseEntity<ProfessorDTO> findById(@RequestParam(required = true) Long id) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.professorService.findById(id));
    }

    @GetMapping("findByName")
    public ResponseEntity<List<ProfessorDTO>> findByName(@RequestParam(required = true) String name, @RequestParam(required = false) Integer from,
                                                         @RequestParam(required = false) Integer to){
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.professorService.findByName(name,from,to));
    }

    @GetMapping("findByLastname")
    public ResponseEntity<List<ProfessorDTO>> findByLastname(@RequestParam(required = true) String lastname, @RequestParam(required = false) Integer from,
                                                         @RequestParam(required = false) Integer to){
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.professorService.findByLastname(lastname,from,to));
    }

    @GetMapping("findByNameAndLastname")
    public ResponseEntity<List<ProfessorDTO>> findByNameAndLastname(@RequestParam(required = true) String name,@RequestParam(required = true) String lastname){
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.professorService.findByNameAndLastname(name,lastname));
    }


}
