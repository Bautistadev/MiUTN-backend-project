package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Enum.PublicationMode;
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

@Entity
@Table(name = "publications")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publication {
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

    @Column(name = "image")
    private String image;

    @Column(name = "expirable", nullable = false)
    private Boolean expirable;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "publication_mode", nullable = false)
    private PublicationMode publicationMode;

    @Column(name = "scheduled_publication_date")
    private LocalDateTime scheduledDate;

}
