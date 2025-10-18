package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Security.exceptions;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.ErrorMessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         org.springframework.security.core.AuthenticationException authException)
            throws IOException, ServletException {

        ErrorMessageDTO errorResponse = ErrorMessageDTO.builder()
                .code(HttpServletResponse.SC_UNAUTHORIZED)
                .status("Unauthorized")
                .timeStamp(LocalDateTime.now())
                .message("Authentication failed: " + authException.getMessage())
                .path(request.getRequestURI())
                .build();

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}