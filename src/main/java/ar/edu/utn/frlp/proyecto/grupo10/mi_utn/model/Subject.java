package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model;

import jakarta.persistence.*;
import lombok.*;
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
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "year",nullable = false)
    private Integer year;
    @Column(name = "type",nullable = false)
    private String type;
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedule;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_id",nullable = false)
    private Career career;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "subjects",fetch = FetchType.EAGER)
    private Set<Professor> professors;

}
