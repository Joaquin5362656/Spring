package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/*
* Configuracion Swagger para la documentacion de la API REST
*
* HTML: http://localhost:8080/swagger-ui/index.html
*/
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Books")
                        .version("1.0")
                        .description("Book API documentation"));
    }

}
