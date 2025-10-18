package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Security.service.contracts;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.LoginResponseDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.UnAuthorizedException;

import javax.security.auth.login.LoginException;

public interface AuthServiceInterface {
    LoginResponseDTO attemptUserPasswordLogin(String user, String email, String password) throws LoginException, UnAuthorizedException, BadRequestException;
    Boolean validateToken(String token);
}
