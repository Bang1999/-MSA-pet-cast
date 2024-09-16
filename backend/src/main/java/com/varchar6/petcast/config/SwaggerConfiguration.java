package com.varchar6.petcast.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "PetCast API Specification",
                description = "Specification for buds",
                version = "v1"
        )
)
@Configuration
public class SwaggerConfiguration {
}
