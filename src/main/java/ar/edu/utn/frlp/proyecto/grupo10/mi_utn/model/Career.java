package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Table(name = "Career")
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql="UPDATE Career SET delete_date = current_timestamp WHERE id = ?")
@SQLRestriction("delete_date IS NULL")
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;

    @CreationTimestamp
    @Column(updatable = false,name = "date",nullable = false)
    private LocalDateTime date;

    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime dateUpdate;

    @Column(name="delete_date")
    private LocalDateTime dateDeleted;

}
