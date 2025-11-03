package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Area;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Enum.TipoNodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long> {

    List<Area> findByPlanoId(String planoId);

    List<Area> findByTipo(TipoNodo tipo);

    @Query("SELECT a FROM Area a WHERE a.planoId = :planoId AND a.tipo IN :tipos")
    List<Area> findByPlanoIdAndTipoIn(@Param("planoId") String planoId, @Param("tipos") List<TipoNodo> tipos);
}