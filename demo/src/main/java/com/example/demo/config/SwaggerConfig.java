package com.example.demo.config;
 
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
@Configuration
public class SwaggerConfig {
 
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Producer API")
                        .version("1.0")
                        .description("API documentation for Producer Service")
                        .contact(new Contact().name("Pavithra").email("pavi@example.com")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project Wiki")
                        .url("https://example.com"));
    }
}