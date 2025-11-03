package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Map;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.MapRepository;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MapService {

    private MapRepository mapRepository;

    public void save (JSONObject mapDTO){
        Map map = new Map();
        map.setJsonData(mapDTO);

        this.mapRepository.save(map);
    }

    public void Update(JSONObject mapDTO){
        Map map = new Map();
        map.setId(1L);
        map.setJsonData(mapDTO);

        this.mapRepository.save(map);
    }

    public String getMap(){
        String map = this.mapRepository.getReferenceById(1L).getJsonData().toString(1);
        return map;
    }

}
