package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "Schedules")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql="UPDATE Schedules SET delete_date = current_timestamp WHERE id = ?")
@SQLRestriction("delete_date IS NULL")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id") // FK en schedules
    private Subject subject;
    @Column(name = "day",nullable = false)
    private String day;
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;
    @Column(updatable = false,name = "date")
    private LocalDateTime date;
    @UpdateTimestamp
    @Column(name = "update_date")
    private LocalDateTime dateUpdate;
    @Column(name="delete_date")
    private LocalDateTime dateDeleted;
}
