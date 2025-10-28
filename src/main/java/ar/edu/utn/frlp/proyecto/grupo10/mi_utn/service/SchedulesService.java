package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository.SchedulesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SchedulesService {

    private SchedulesRepository schedulesRepository;

    public List<String> findDistinctClassroomBy(){
        return this.schedulesRepository.findAllDistinctClassrooms();
    }
}
