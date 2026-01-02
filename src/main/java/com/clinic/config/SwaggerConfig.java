package com.clinic.config;

import static com.clinic.constant.ConfigConstants.*;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                                .addSecurityItem(
                                                new io.swagger.v3.oas.models.security.SecurityRequirement()
                                                                .addList(SECURITY_SCHEME_NAME))
                                .components(new io.swagger.v3.oas.models.Components()
                                                .addSecuritySchemes(SECURITY_SCHEME_NAME,
                                                                new io.swagger.v3.oas.models.security.SecurityScheme()
                                                                                .name(SECURITY_SCHEME_NAME)
                                                                                .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                                                                                .scheme(SECURITY_SCHEME_TYPE)
                                                                                .bearerFormat(SECURITY_BEARER_FORMAT)))
                                .info(new Info()
                                                .title(API_TITLE)
                                                .version(API_VERSION)
                                                .description(API_DESCRIPTION));
        }
}