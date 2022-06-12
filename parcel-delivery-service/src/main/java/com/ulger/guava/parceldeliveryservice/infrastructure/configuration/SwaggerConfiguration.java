package com.ulger.guava.parceldeliveryservice.infrastructure.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                            .title("Parcel Delivery Service API")
                            .description("Application to manage parcels")
                            .version("v1.0"))
                .externalDocs(
                        new ExternalDocumentation()
                            .description("Parcel Delivery Service Full Code&Documentation")
                            .url("https://github.com/tmp-ahmedulger/guava"));
    }
}