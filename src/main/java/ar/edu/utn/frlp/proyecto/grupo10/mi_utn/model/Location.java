package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Table(name = "Location")
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false,name = "date",nullable = false)
    private LocalDateTime date;

    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime dateUpdate;

    @Column(name="delete_date")
    private LocalDateTime dateDeleted;
}
