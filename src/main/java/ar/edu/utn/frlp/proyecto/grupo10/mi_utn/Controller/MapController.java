package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Controller;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Map;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.MapService;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/map")
@AllArgsConstructor
public class MapController {

    private MapService mapService;

    @PostMapping("/saveJSON")
    public ResponseEntity<Void> save( @RequestBody String jsonString){
        System.out.println("JSON recibido como String: " + jsonString);

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            System.out.println("JSONObject convertido: " + jsonObject.toString());

            this.mapService.save(jsonObject);
            return ResponseEntity.ok().build();
        } catch (JSONException e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getPoint")
    public ResponseEntity<String> getPoint(){
        return ResponseEntity.ok(this.mapService.getMap());
    }

    @PutMapping("/updatePoint")
    public ResponseEntity<Void> update(@RequestBody String jsonString){
        System.out.println("JSON recibido como String: " + jsonString);

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            System.out.println("JSONObject convertido: " + jsonObject.toString());

            this.mapService.Update(jsonObject);
            return ResponseEntity.ok().build();
        } catch (JSONException e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
