package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageDTO {
    private String message;
    private String status;
    private Integer code;
    private String path;
    private LocalDateTime timeStamp;
}
