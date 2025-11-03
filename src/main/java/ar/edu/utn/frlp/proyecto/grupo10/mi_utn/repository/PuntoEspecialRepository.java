package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Enum.TipoNodo;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.PuntoEspecial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// PuntoEspecialRepository.java
@Repository
public interface PuntoEspecialRepository extends JpaRepository<PuntoEspecial, Long> {

    List<PuntoEspecial> findByPlanoId(String planoId);

    List<PuntoEspecial> findByTipo(TipoNodo tipo);

    List<PuntoEspecial> findByPlanoIdAndTipo(String planoId, TipoNodo tipo);

    @Query("SELECT COUNT(p) FROM PuntoEspecial p WHERE p.tipo = :tipo")
    Long countByTipo(@Param("tipo") TipoNodo tipo);
}