package com.sts.first.CustomerManagement.config;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {
    //This is for integrating Springdoc OpenAPI with a Spring Boot application
    // to auto-generate the API documentation and provide a Swagger UI.
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }
}
