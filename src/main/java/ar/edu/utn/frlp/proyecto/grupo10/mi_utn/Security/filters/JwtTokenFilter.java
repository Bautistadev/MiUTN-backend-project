package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Security.filters;


import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Security.jwt.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@Setter
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtProvider;
    private PathPatternRequestMatcher[] publicRoutes;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        boolean isPublicRoute = isPublicRoute(request);
        if (!isPublicRoute) {
            String token = this.getToken(request);
            if (token != null && jwtProvider.validateToken(token)) {
                String username = jwtProvider.getUsername(token);
                List<GrantedAuthority> authorities = jwtProvider.getAuthoritiesFromToken(token);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }


    private boolean isPublicRoute(HttpServletRequest request) {
        for (PathPatternRequestMatcher matcher : publicRoutes) {
            if (matcher.matches(request)) {
                return true;
            }
        }
        return false;
    }

    private String getToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }


}