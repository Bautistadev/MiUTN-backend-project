package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Security.service.Impl;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.LoginResponseDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.DTO.response.UserDTO;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Security.jwt.JwtTokenProvider;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Security.service.contracts.AuthServiceInterface;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadCredentialssException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.BadRequestException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.customs.UnAuthorizedException;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceInterface {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponseDTO attemptUserPasswordLogin(String user, String email, String password) throws LoginException, UnAuthorizedException, BadRequestException {
        if(user != null){
            if(this.userService.existsByUsername(user)) {
                UserDTO u = this.userService.findByUsername(user);
                if(!passwordEncoder.matches(password,u.getPassword()))
                    throw new BadCredentialssException("Bad credentials exception");
                return LoginResponseDTO.builder()
                        .accessToken(jwtTokenProvider.generateToken(u))
                        .build();
            }
        }
        if(this.userService.existsByEmail(email)){
            UserDTO u = this.userService.findByEmail(email);
            if(!passwordEncoder.matches(password,u.getPassword()))
                throw new BadCredentialssException("Bad credentials exception");
            return LoginResponseDTO.builder()
                    .accessToken(jwtTokenProvider.generateToken(u))
                    .build();
        }

        throw new UnAuthorizedException("User is not authorized");

    }

    @Override
    public Boolean validateToken(String token) {
        return this.jwtTokenProvider.validateToken(token);
    }
}
