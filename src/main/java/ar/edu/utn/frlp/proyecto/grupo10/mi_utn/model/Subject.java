package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Subjects")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql="UPDATE Subject SET delete_date = current_timestamp WHERE id = ?")
@SQLRestriction("delete_date IS NULL")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "commission",nullable = false)
    private String commission;
    @Column(name = "year",nullable = false)
    private Integer year;
    @Column(name = "type",nullable = false)
    private String type;
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedule;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "career_id",nullable = false)
    private Career career;
    @ManyToMany(mappedBy = "subjects")
    private Set<Professor> professors;
    @CreationTimestamp
    @Column(updatable = false,name = "date")
    private LocalDateTime date;
    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime dateUpdate;
    @Column(name="delete_date")
    private LocalDateTime dateDeleted;

}
