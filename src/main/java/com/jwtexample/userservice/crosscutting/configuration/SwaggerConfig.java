package com.jwtexample.userservice.crosscutting.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    OpenAPI springShopOpenAPI() { // for general information in OpenAPI documentation
        return new OpenAPI()
                .info(new Info().title("User Service API")
                        .description("This service ilustrates how Spring Security authentication and authorization works")
                        .version("v0.0.1"))
                .externalDocs(new ExternalDocumentation()
                        .description("springdoc-openapi")
                        .url("http://springdoc.org"));
    }
}
