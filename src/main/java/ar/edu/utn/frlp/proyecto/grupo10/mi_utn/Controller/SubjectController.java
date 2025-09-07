package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Controller;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.SubjectRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.SubjectDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.SubjectMapDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.SubjectService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/MiUTN/subject")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class SubjectController {

    private SubjectService subjectService;

    @GetMapping("/")
    public ResponseEntity<Map<Long, SubjectMapDTO>> findAllMap(){
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(this.subjectService.findAll());
    }

    @GetMapping("/findById")
    public ResponseEntity<SubjectDTO> findById(@RequestParam Long id) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.subjectService.findById(id));
    }

    @GetMapping("findAll")
    public ResponseEntity<List<SubjectDTO>> findAll(@RequestParam(required = false) Integer from, @RequestParam(required = false) Integer to){
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.subjectService.findAll(from, to));
    }

    @PostMapping("save")
    public ResponseEntity<Void> save(@RequestBody @Valid SubjectRequestDTO subjectRequestDTO){
        this.subjectService.save(subjectRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("update")
    public ResponseEntity<Void> update(@RequestBody @Valid SubjectDTO subjectDTO) throws BadRequestException {
        this.subjectService.update(subjectDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> delete(@RequestParam Long id) throws BadRequestException {
        this.subjectService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("findByCommissionId")
    public ResponseEntity<List<SubjectDTO>> findByCommissionId(@RequestParam  Long commissionId, @RequestParam(required = false) Integer from, @RequestParam(required = false) Integer to){
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.subjectService.findByCommissionId(commissionId,from,to));
    }

    @GetMapping("findByYear")
    public ResponseEntity<List<SubjectDTO>> findByYear(@RequestParam  Integer year, @RequestParam(required = false) Integer from, @RequestParam(required = false) Integer to){
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.subjectService.findByYear(year,from,to));
    }

    @GetMapping("findByType")
    public ResponseEntity<List<SubjectDTO>> findByType(@RequestParam  String type, @RequestParam(required = false) Integer from, @RequestParam(required = false) Integer to){
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.subjectService.findByType(type,from,to));
    }

    @GetMapping("findByCareerId")
    public ResponseEntity<List<SubjectDTO>> findByCareerId(@RequestParam  Long careerId, @RequestParam(required = false) Integer from, @RequestParam(required = false) Integer to){
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.subjectService.findByCareerId(careerId,from,to));
    }

    @GetMapping("findByCareerName")
    public ResponseEntity<List<SubjectDTO>> findByCareerName(@RequestParam  String careerName, @RequestParam(required = false) Integer from, @RequestParam(required = false) Integer to){
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.subjectService.findByCareerName(careerName,from,to));
    }

    @GetMapping("findByName")
    public ResponseEntity<SubjectDTO> findByName(@RequestParam String name) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.subjectService.findByName(name));
    }

}
