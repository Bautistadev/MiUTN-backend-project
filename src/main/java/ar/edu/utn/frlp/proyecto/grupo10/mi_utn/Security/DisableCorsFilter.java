package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class DisableCorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // Sobrescribe cualquier cabecera CORS
        response.setHeader("Access-Control-Allow-Origin", "");
        response.setHeader("Access-Control-Allow-Methods", "");
        response.setHeader("Access-Control-Allow-Headers", "");
        response.setHeader("Access-Control-Allow-Credentials", "false");

        filterChain.doFilter(request, response);
    }
}