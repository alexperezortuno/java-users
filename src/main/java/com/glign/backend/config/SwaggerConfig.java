package com.glign.backend.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    String schemaName = "bearerAuth";
    String schemaDescription = "Basic Authentication";
    SecurityScheme.Type schemaType = SecurityScheme.Type.HTTP;
    String bearerFormat = "JWT";
    String scheme = "bearer";

    @Bean
    public OpenAPI caseOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(schemaName))
                .components(new Components()
                        .addSecuritySchemes(schemaName, new SecurityScheme()
                                .name(scheme)
                                .type(schemaType)
                                .bearerFormat(bearerFormat)
                                .in(SecurityScheme.In.HEADER)
                                .scheme(scheme)))
                .info(new Info()
                        .title("Bearer Authentication")
                        .description(schemaDescription)
                        .version("1.0")
                );
    }
}
