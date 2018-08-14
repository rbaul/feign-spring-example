package com.example.feign.client.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@EnableConfigurationProperties(RestClientProperties.class)
public class RestClientAutoConfiguration {

    @Configuration
    @ComponentScan(basePackages = "com.example.feign.client")
    public static class RestApiConfiguration {

    }
}
