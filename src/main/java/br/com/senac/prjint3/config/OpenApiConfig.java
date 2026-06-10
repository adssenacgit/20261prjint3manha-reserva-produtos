package br.com.senac.prjint3.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Projeto Integrador 3")
                        .description("CRUD REST com Java 21, Spring Boot, MySQL e apagado lógico por status.")
                        .version("1.0.0")
                        .license(new License().name("Uso acadêmico")));
    }
}
