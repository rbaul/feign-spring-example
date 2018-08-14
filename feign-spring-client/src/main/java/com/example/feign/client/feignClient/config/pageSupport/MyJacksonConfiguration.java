package com.example.feign.client.feignClient.config.pageSupport;

import com.fasterxml.jackson.databind.Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyJacksonConfiguration {

    @Bean
    public Module myJacksonModule() {
        return new MyJacksonModule();
    }
}
