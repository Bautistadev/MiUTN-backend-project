package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDTO {
    private String accessToken;
}
