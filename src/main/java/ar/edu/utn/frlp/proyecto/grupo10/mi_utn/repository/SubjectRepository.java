package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    public List<Subject> findByCommission(String commission);
    public Page<Subject> findByCommission(String commission, Pageable pageable);
    public List<Subject> findByYear(Integer year);
    public Page<Subject> findByYear(Integer year,Pageable pageable);
    public List<Subject> findByType(String type);
    public Page<Subject> findByType(String type,Pageable pageable);
    public List<Subject> findByCareerId(Long careerId);
    public Page<Subject> findByCareerId(Long careerId,Pageable pageable);
    public List<Subject> findByCareerName(String name);
    public Page<Subject> findByCareerName(String name,Pageable pageable);



}
