package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Enum.Role;
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
@Table(name="users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql="UPDATE users SET delete_date = current_timestamp WHERE id = ?")
@SQLRestriction("delete_date IS NULL")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username",nullable = false)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "role",nullable = false)
    private Role role;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "person_id",nullable = false)
    private Professor person;
    @Column(updatable = false,name = "create_date")
    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime dateUpdate;
    @Column(name="delete_date")
    private LocalDateTime dateDeleted;
}
