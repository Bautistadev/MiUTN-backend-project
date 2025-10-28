package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Controller;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.SchedulesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/MiUTN/schedules")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class SchedulesController {

    private SchedulesService schedulesService;

    @GetMapping("/findAllClassroom")
    public ResponseEntity<List<String>> getAllClassroom(){
        return ResponseEntity.status(HttpStatus.OK).body(this.schedulesService.findDistinctClassroomBy());
    }
}
