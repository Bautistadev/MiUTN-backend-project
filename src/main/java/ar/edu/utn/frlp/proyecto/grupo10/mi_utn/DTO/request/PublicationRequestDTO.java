package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Enum.PublicationMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationRequestDTO {
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String content;
    @NotNull
    private Boolean hidden;
    @NotNull
    private Boolean priority;
    @NotBlank
    private String image;
    @NotNull
    private Boolean expirable;

    private LocalDateTime expirationDate;
    @NotNull
    private PublicationMode publicationMode;

    private LocalDateTime scheduledDate;
}
