package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Controller;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.CommissionRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.CommissionDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.CommissionRespository;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.CommissionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/MiUTN/commission")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CommissionController {

    private CommissionService commissionService;

    @GetMapping("findById")
    public ResponseEntity<CommissionDTO> findById(@RequestParam Long id) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(this.commissionService.findById(id));
    }

    @GetMapping("findByName")
    public ResponseEntity<CommissionDTO> findByName(@RequestParam String name) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(this.commissionService.findByName(name));

    }

    @GetMapping("findAll")
    public ResponseEntity<List<CommissionDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(this.commissionService.findAll());
    }

    @PostMapping("save")
    public ResponseEntity<Void> save(@RequestBody @Valid CommissionRequestDTO commissionRequestDTO){
        this.commissionService.save(commissionRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("update")
    public ResponseEntity<Void> update(@RequestBody @Valid CommissionDTO commissionDTO) throws BadRequestException {
        this.commissionService.update(commissionDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> delete(@RequestParam Long id) throws BadRequestException {
        this.commissionService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
