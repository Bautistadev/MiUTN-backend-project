package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Security.exceptions;

import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.exceptions.ErrorMessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       org.springframework.security.access.AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        ErrorMessageDTO errorResponse = ErrorMessageDTO.builder().code(HttpServletResponse.SC_FORBIDDEN)
                .status("Forbidden")
                .timeStamp(LocalDateTime.now())
                .message("Access denied: " + accessDeniedException.getMessage())
                .path(request.getRequestURI())
                .build();

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
