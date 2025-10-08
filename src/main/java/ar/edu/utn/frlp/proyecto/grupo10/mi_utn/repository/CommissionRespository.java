package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Commission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommissionRespository extends JpaRepository<Commission,Long> {
    public Boolean existsByName(String name);
    public Optional<Commission> findByName(String name);
}
