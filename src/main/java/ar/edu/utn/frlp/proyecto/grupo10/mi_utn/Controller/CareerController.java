package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Controller;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.CareerRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.CareerDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.CareerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/MiUTN/career")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CareerController{

    private CareerService careerService;

    @PostMapping("save")
    public ResponseEntity<Void> save(@RequestBody @Valid CareerRequestDTO careerRequestDTO){
        this.careerService.save(careerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("update")
    public ResponseEntity<Void> update(@RequestBody @Valid CareerDTO careerDTO) throws BadRequestException {
        this.careerService.update(careerDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> delete(@RequestParam Long id) throws BadRequestException {
        this.careerService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("findAll")
    public ResponseEntity<List<CareerDTO>> findAll(@RequestParam(required = false) Integer from, @RequestParam(required = false) Integer to){
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.careerService.findAll(from,to));
    }

    @GetMapping("findByName")
    public ResponseEntity<CareerDTO> findByName(@RequestParam String name) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.careerService.findByName(name));
    }

    @GetMapping("findById")
    public ResponseEntity<CareerDTO> findById(@RequestParam Long id) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(this.careerService.findById(id));
    }

}
