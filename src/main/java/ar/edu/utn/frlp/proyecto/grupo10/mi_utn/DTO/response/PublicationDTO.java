package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.model.Enum.PublicationMode;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicationDTO {
    @NotBlank
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String content;
    @NotBlank
    private Boolean hidden;
    @NotBlank
    private Boolean priority;
    @NotBlank
    private String imagePath;
    @NotBlank
    private Boolean expirable;
    @NotBlank
    private LocalDateTime expirationDate;
    @NotBlank
    private PublicationMode publicationMode;
    @NotBlank
    private LocalDateTime scheduledDate;
}
