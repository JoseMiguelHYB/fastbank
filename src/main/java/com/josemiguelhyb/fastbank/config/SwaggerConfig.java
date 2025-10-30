package com.josemiguelhyb.fastbank.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI fastBankOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("💸 FastBank API")
                        .description("API REST para operaciones bancarias con concurrencia, baja latencia y transacciones seguras.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("José Miguel H.Y.B")
                                .email("josemiguelhyb@example.com")
                                .url("https://linkedin.com/in/josemiguelhyb"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")));
    }
}
