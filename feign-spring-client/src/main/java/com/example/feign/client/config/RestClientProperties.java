package com.example.feign.client.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "example.client.api")
public class RestClientProperties {
    /**
     * API location of application service.
     */
    private String path = "http://localhost:8080";

}
