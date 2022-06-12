package com.ulger.guava.parceldeliveryservice.infrastructure.configuration;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("parcel-delivery-service")
                .pathsToMatch("/**")
                .build();
    }
}