package com.example.feign.client.feignClient.config;

import com.example.feign.client.feignClient.config.pageSupport.PageableQueryEncoder;
import feign.Feign;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ClientConfig {

    @Bean
    public PageableQueryEncoder extendedEncoder(ObjectFactory<HttpMessageConverters> messageConverters){
        return new PageableQueryEncoder(new SpringEncoder(messageConverters));
    }

    @Bean
    public ErrorDecoder errorDecoder(){
        return new ClientErrorDecoder2();
    }

//    @Bean
//    @Scope("prototype")
//    public Feign.Builder feignBuilder() {
//        return Feign.builder();
//    }
}
