package com.example.feign.client.feignClient.config;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public ErrorDecoder errorDecoder(){
        return new ClientErrorDecoder();
    }
}
