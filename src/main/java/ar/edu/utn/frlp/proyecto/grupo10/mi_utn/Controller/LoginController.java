package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Controller;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.request.LoginDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.LoginResponseDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Security.service.Impl.AuthService;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.UnAuthorizedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.LoginException;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class LoginController {
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(description = "Login with user and password.", summary = "Login with user and password")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginDTO userLoginRequestDTO) throws LoginException, UnAuthorizedException, BadRequestException {
        return new ResponseEntity<>(authService.attemptUserPasswordLogin(userLoginRequestDTO.getUsername(),userLoginRequestDTO.getEmail(), userLoginRequestDTO.getPassword()), HttpStatus.OK);
    }
}
