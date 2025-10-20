package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationRepository extends JpaRepository<Publication,Long> {
}
