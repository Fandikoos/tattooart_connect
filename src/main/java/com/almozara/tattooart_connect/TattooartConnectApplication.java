package com.almozara.tattooart_connect;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "TattooArtConnect API",
                version = "1.0",
                description = "Documentación de la API de TattoArt Connect"
        )
)
@SpringBootApplication
public class TattooartConnectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TattooartConnectApplication.class, args);
    }

}
