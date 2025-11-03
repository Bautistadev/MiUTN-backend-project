package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// FloorRepository.java
@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {

    Optional<Floor> findByName(String name);

    List<Floor> findAllByOrderByNameAsc();
}