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
import org.springframework.boot.autoconfigure.graphql.ConditionalOnGraphQlSchema;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "commissions")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql="UPDATE Commssion SET delete_date = current_timestamp WHERE id = ?")
@SQLRestriction("delete_date IS NULL")
public class Commission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;
    @OneToMany(mappedBy = "commission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Schedule> schedule;
    @CreationTimestamp
    @Column(updatable = false,name = "date")
    private LocalDateTime date;
    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime dateUpdate;
    @Column(name="delete_date")
    private LocalDateTime dateDeleted;

}
