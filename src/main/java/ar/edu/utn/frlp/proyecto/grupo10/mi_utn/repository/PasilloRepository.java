package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository;


import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Pasillo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PasilloRepository extends JpaRepository<Pasillo, Long> {

    List<Pasillo> findByPlanoId(String planoId);

    List<Pasillo> findByCareerId(Long careerId);

    List<Pasillo> findByFloorId(Long floorId);

    List<Pasillo> findByDesdeIdOrHastaId(Long desdeId, Long hastaId);

    @Query("SELECT p FROM Pasillo p WHERE p.desde.id = :nodoId OR p.hasta.id = :nodoId")
    List<Pasillo> findByNodoId(@Param("nodoId") Long nodoId);

    @Query("SELECT p FROM Pasillo p WHERE (p.desde.id = :nodoId1 AND p.hasta.id = :nodoId2) " +
            "OR (p.desde.id = :nodoId2 AND p.hasta.id = :nodoId1)")
    Optional<Pasillo> findPasilloEntreNodos(@Param("nodoId1") Long nodoId1, @Param("nodoId2") Long nodoId2);

    @Query("SELECT COUNT(p) FROM Pasillo p WHERE p.desde.id = :nodoId OR p.hasta.id = :nodoId")
    Long countConexionesByNodoId(@Param("nodoId") Long nodoId);

    void deleteByDesdeIdOrHastaId(Long desdeId, Long hastaId);
}