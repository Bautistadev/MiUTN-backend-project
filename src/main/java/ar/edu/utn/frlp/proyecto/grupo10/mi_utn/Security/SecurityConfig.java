package ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Security;


import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Security.exceptions.JwtAccessDeniedHandler;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Security.exceptions.JwtAuthenticationEntryPoint;
import ar.edu.utn.frlp.proyecto.grupo10.mi_utn.Security.filters.JwtTokenFilter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final String PATH = "/api/v1/miUTN/";
    private final JwtTokenFilter jwtTokenFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        PathPatternRequestMatcher[] publicRoutes = {
                PathPatternRequestMatcher.withDefaults().matcher("/login/**"),
                PathPatternRequestMatcher.withDefaults().matcher("/swagger-ui/**"),
                PathPatternRequestMatcher.withDefaults().matcher("/v3/api-docs/**")
        };

        PathPatternRequestMatcher[] adminRoutes = {
                PathPatternRequestMatcher.withDefaults().matcher("/login/**"),
                PathPatternRequestMatcher.withDefaults().matcher("/swagger-ui/**"),
                PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.DELETE,this.PATH.concat("persons/**")),
                PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.GET,this.PATH.concat("users/**")),
                PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.PUT,this.PATH.concat("users/**")),
                PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.POST,this.PATH.concat("users/**")),
                PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.GET,this.PATH.concat("users/**")),
        };

        PathPatternRequestMatcher[] userRouter = {
                PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.PUT,this.PATH.concat("persons/**")),
                PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.POST,this.PATH.concat("persons/**")),
                PathPatternRequestMatcher.withDefaults().matcher(HttpMethod.GET,this.PATH.concat("persons/**")),
                PathPatternRequestMatcher.withDefaults().matcher("/swagger-ui/**")
        };


        jwtTokenFilter.setPublicRoutes(publicRoutes);
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(publicRoutes).permitAll()
                        .anyRequest().permitAll())
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Desactiva sesiones
                .addFilterAfter(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler))
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Arrays.asList("http://localhost:7000", "http://localhost:7001", "http://localhost:5173","*"));
                    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    return config;
                }))
                .build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*") // o limitar a tu dominio/ngrok
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

