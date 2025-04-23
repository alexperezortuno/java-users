package com.glign.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Value("${userApi.openapi.dev-url}")
    private String devUrl;

    @Value("${userApi.openapi.prod-url}")
    private String prodUrl;

    @Value("${userApi.openapi.email}")
    private String email;

    @Value("${userApi.openapi.name}")
    private String name;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail(email);
        contact.setName(name);
        contact.setUrl(prodUrl);

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Tutorial Management API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage the application.").termsOfService(prodUrl.concat("/terms"))
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
