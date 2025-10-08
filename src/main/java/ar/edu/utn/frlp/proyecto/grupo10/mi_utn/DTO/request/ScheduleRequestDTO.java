package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDTO {
    @NotBlank
    private String day;
    @NotBlank
    private LocalTime startTime;
    @NotBlank
    private LocalTime endTime;
}
