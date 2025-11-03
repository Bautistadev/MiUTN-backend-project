package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

// GPSRepository.java
@Repository
public class GPSRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Object[]> obtenerGrafoCompleto() {
        String query = """
            SELECT 
                n.id as nodo_id,
                n.nombre as nodo_nombre,
                n.tipo as nodo_tipo,
                n.plano_id as plano_id,
                n.coordenada_x as x,
                n.coordenada_y as y,
                c.id as career_id,
                c.name as career_name,
                f.id as floor_id,
                f.name as floor_name
            FROM nodos n
            LEFT JOIN career c ON n.career_id = c.id
            LEFT JOIN floor f ON n.floor_id = f.id
            WHERE n.delete_date IS NULL
            """;

        return entityManager.createNativeQuery(query).getResultList();
    }

    public List<Object[]> obtenerConexionesCompletas() {
        String query = """
            SELECT 
                p.id as pasillo_id,
                p.nombre as pasillo_nombre,
                p.desde_nodo_id as desde_id,
                p.hasta_nodo_id as hasta_id,
                p.distancia as distancia,
                d.nombre as desde_nombre,
                h.nombre as hasta_nombre
            FROM pasillos p
            LEFT JOIN nodos d ON p.desde_nodo_id = d.id
            LEFT JOIN nodos h ON p.hasta_nodo_id = h.id
            WHERE p.delete_date IS NULL AND d.delete_date IS NULL AND h.delete_date IS NULL
            """;

        return entityManager.createNativeQuery(query).getResultList();
    }

    public List<Object[]> obtenerEscalerasConectadas() {
        String query = """
            SELECT 
                e1.id as escalera1_id,
                e1.nombre as escalera1_nombre,
                e2.id as escalera2_id, 
                e2.nombre as escalera2_nombre
            FROM escaleras e1
            LEFT JOIN escaleras e2 ON e1.escalera_conectada_id = e2.id
            WHERE e1.delete_date IS NULL AND (e2.id IS NULL OR e2.delete_date IS NULL)
            """;

        return entityManager.createNativeQuery(query).getResultList();
    }
}