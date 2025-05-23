package com.almozara.tattooart_connect.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Aplica a todos los endpoints
                .allowedOrigins("http://localhost:4200")  // URL de Angular en desarrollo
                .allowedOrigins("*")  // En producción usa dominios específicos en vez de *
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);  // Tiempo de cacheo de pre-flight requests
    }
}
