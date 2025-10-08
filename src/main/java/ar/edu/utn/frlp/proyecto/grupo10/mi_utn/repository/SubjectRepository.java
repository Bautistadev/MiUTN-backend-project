package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.repository;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    public Boolean existsByName(String name);
    public Optional<Subject> findByName(String name);
    List<Subject> findByScheduleCommissionId(Long commissionId);
    Page<Subject> findByScheduleCommissionId(Long commissionId,Pageable pageable);
    public List<Subject> findByYear(Integer year);
    public Page<Subject> findByYear(Integer year,Pageable pageable);
    public List<Subject> findByType(String type);
    public Page<Subject> findByType(String type,Pageable pageable);
    public List<Subject> findByCareerId(Long careerId);
    public Page<Subject> findByCareerId(Long careerId,Pageable pageable);
    public List<Subject> findByCareerName(String name);
    public Page<Subject> findByCareerName(String name,Pageable pageable);
    @Query("""
        SELECT DISTINCT s 
        FROM Subject s
        JOIN s.career c
        JOIN s.schedule sch
        JOIN sch.commission com
        WHERE c.name = :careerName
          AND s.year = :year
          AND com.name = :commissionName
    """)
    List<Subject> findByCareerNameYearAndCommissionName(
            @Param("careerName") String careerName,
            @Param("year") Integer year,
            @Param("commissionName") String commissionName
    );


}
