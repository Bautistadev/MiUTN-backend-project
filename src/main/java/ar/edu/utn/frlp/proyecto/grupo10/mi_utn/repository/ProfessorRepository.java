package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long> {
    public List<Professor> findByName(String name);
    public Page<Professor> findByName(String name, Pageable pageable);
    public List<Professor> findByLastname(String lastname);
    public Page<Professor> findByLastname(String lastname, Pageable pageable);
    public Boolean existsByNameAndLastname(String name, String lastname);
    public List<Professor> findByNameAndLastname(String name, String lastname);
}
