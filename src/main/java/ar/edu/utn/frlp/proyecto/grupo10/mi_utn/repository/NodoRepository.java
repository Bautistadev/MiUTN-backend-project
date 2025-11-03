package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Enum.TipoNodo;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Nodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// NodoRepository.java
@Repository
public interface NodoRepository extends JpaRepository<Nodo, Long> {

    List<Nodo> findByPlanoId(String planoId);

    List<Nodo> findByCareerId(Long careerId);

    List<Nodo> findByFloorId(Long floorId);

    List<Nodo> findByPlanoIdAndCareerId(String planoId, Long careerId);

    List<Nodo> findByTipo(TipoNodo tipo);

    List<Nodo> findByPlanoIdAndTipo(String planoId, TipoNodo tipo);

    @Query("SELECT n FROM Nodo n WHERE n.career.id = :careerId AND n.floor.id = :floorId")
    List<Nodo> findByCareerAndFloor(@Param("careerId") Long careerId, @Param("floorId") Long floorId);

    @Query("SELECT COUNT(n) FROM Nodo n WHERE n.tipo = :tipo")
    Long countByTipo(@Param("tipo") TipoNodo tipo);
}