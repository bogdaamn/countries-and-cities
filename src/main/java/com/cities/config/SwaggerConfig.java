package com.cities.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI configureSwagger(){
        return new OpenAPI().components(new Components())
                .info(new Info()
                        .title("Countries and cities")
                        .version("1.0.0")
                        .description("Hi! Nice to see you. Try out this application!")
                );
    }
}
