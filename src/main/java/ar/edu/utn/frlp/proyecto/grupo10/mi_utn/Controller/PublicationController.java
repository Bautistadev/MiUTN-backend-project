package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Controller;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.PublicationRequestDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.PublicationDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Publication;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.PublicationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/miUTN/publication")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class PublicationController {

    private final PublicationService publicationService;

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Publication> save(@ModelAttribute @Valid PublicationRequestDTO request){
        Publication response = publicationService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<PublicationDTO>> findAll(){
        List<PublicationDTO> publications = publicationService.findAll(0,0);
        return ResponseEntity.status(HttpStatus.OK).body(publications);
    }

    @GetMapping("/findById")
    public ResponseEntity<PublicationDTO> findById(@RequestParam Long id) throws BadRequestException {
        PublicationDTO publication = publicationService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(publication);
    }

    @PutMapping("/update")
    public ResponseEntity<Publication> update(@RequestBody @Valid PublicationRequestDTO request) throws BadRequestException{
        Publication response = publicationService.update(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam Long id) throws BadRequestException {
        try {
            publicationService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (BadRequestException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
