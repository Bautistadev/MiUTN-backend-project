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
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Entity
@Table(name = "Publications")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql="UPDATE Publications SET delete_date = current_timestamp WHERE id = ?")
@SQLRestriction("delete_date IS NULL")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "hidden",nullable = false)
    private Boolean hidden;

    @Column(name = "image",nullable = false)
    private String image;

    @CreationTimestamp
    @Column(updatable = false,name = "date",nullable = false)
    private LocalDateTime date;

    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime dateUpdate;

    @Column(name="delete_date")
    private LocalDateTime dateDeleted;
}
