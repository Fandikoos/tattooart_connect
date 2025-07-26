package com.almozara.tattooart_connect.security.config;

import com.almozara.tattooart_connect.config.ApiConfig;
import com.almozara.tattooart_connect.controller.ArtistController;
import com.almozara.tattooart_connect.controller.StudioController;
import com.almozara.tattooart_connect.security.controller.AuthController;
import com.almozara.tattooart_connect.security.jwt.JwtEntryPoint;
import com.almozara.tattooart_connect.security.jwt.JwtFilter;
import com.almozara.tattooart_connect.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class MainSecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtEntryPoint jwtEntryPoint;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // Nueva forma de deshabilitar CSRF
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Habilitar Cors con mi configuración
                .authorizeHttpRequests(auth -> auth
                        // TODO Agrupar rutias públicas en una clase Utils
                        // Rutas públicas que no requieren de Token
                                .requestMatchers(ApiConfig.API_BASE_PATH + AuthController.URL + "/**").permitAll()
                                .requestMatchers(ApiConfig.API_BASE_PATH + ArtistController.URL + "/**").permitAll()
                                .requestMatchers(ApiConfig.API_BASE_PATH + StudioController.URL + "/**").permitAll()

                        // Rutas privadas que si que requieren token
//                         .requestMatchers(ApiConfig.API_BASE_PATH + StudioController.URL + "/protected/**").authenticated() // Ejemplo futuro
                        // .requestMatchers(ApiConfig.API_BASE_PATH + "/appointments/**").authenticated() // Ejemplo futuro

                        .anyRequest().authenticated() // Por defecto pedir autenticación
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(jwtEntryPoint)  // Nueva forma de configurar manejo de excepciones
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Nueva forma de configurar gestión de sesión
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
