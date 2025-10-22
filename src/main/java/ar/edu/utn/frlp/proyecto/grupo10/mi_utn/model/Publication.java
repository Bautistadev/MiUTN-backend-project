package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Enum.PublicationMode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "publications")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publication implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "description",nullable = false)
    private String description;

    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "hidden", nullable = false)
    private Boolean hidden;

    @Column(name = "priority", nullable = false)
    private Boolean priority;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "expirable", nullable = false)
    private Boolean expirable;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "publication_mode", nullable = false)
    private PublicationMode publicationMode;

    @Column(name = "scheduled_publication_date")
    private LocalDateTime scheduledDate;

    @CreationTimestamp
    @Column(updatable = false,name = "date",nullable = false)
    private LocalDateTime date;

    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime dateUpdate;

    @Column(name="delete_date")
    private LocalDateTime dateDeleted;

}
