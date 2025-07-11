package com.mySkin.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("MySkin API")
                .description("Trabalho prático 02 - Sistema de gerenciamento de Review")
                .version("1.0")
                .license(new License().name("Apache 2.0").url("http://www.myskin.com.br"))
        );
    }

}