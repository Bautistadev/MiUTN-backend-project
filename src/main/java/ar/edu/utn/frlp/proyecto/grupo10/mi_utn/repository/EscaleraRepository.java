package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Escalera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface EscaleraRepository extends JpaRepository<Escalera, Long> {

    // ✅ MÉTODOS QUE SÍ FUNCIONAN (con propiedades directas de Escalera)
    List<Escalera> findByPisoActualId(Long pisoActualId);
    List<Escalera> findByCarreraActualId(Long carreraActualId);
    List<Escalera> findByPisoActualIdAndCarreraActualId(Long pisoActualId, Long carreraActualId);
    List<Escalera> findAllByIdIn(List<Long> ids);

    // ✅ MÉTODOS QUE NECESITAN @QUERY (para propiedades en relaciones)

    // Encontrar escaleras que tengan un piso específico como destino
    @Query("SELECT DISTINCT e FROM Escalera e JOIN e.destinos d WHERE d.pisoDestino.id = :pisoDestinoId")
    List<Escalera> findEscalerasByPisoDestino(@Param("pisoDestinoId") Long pisoDestinoId);

    // Encontrar escaleras que tengan una carrera específica como destino
    @Query("SELECT DISTINCT e FROM Escalera e JOIN e.destinos d WHERE d.carreraDestino.id = :carreraDestinoId")
    List<Escalera> findEscalerasByCareerDestino(@Param("carreraDestinoId") Long carreraDestinoId);

    // 🔥 ELIMINAR o CORREGIR este método que causa el error:
    // ❌ findByCarreraDestinoIdAndPisoDestinoId - ESTE MÉTODO NO EXISTE MÁS

    // ✅ En su lugar, usar este método con @Query:
    @Query("SELECT DISTINCT e FROM Escalera e JOIN e.destinos d WHERE d.carreraDestino.id = :carreraDestinoId AND d.pisoDestino.id = :pisoDestinoId")
    List<Escalera> findEscalerasByDestinoCompleto(@Param("carreraDestinoId") Long carreraDestinoId, @Param("pisoDestinoId") Long pisoDestinoId);

    // Encontrar escaleras conectadas a una escalera específica
    @Query("SELECT DISTINCT e FROM Escalera e JOIN e.conexiones c WHERE c.escaleraDestino.id = :escaleraId")
    List<Escalera> findEscalerasConectadasA(@Param("escaleraId") Long escaleraId);
}