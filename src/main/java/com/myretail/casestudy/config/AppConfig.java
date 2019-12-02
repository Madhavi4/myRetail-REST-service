package com.myretail.casestudy.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * The application configuration class
 */
@Configuration
public class AppConfig {

    /**
     * RestTempate bean
     * @param builder RestTemplateBuilder
     * @return RestTemplate
     */
    @Bean
    public RestTemplate redskyRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
