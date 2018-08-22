package com.example.feign.client.feignClient.config;

import com.example.feign.client.feignClient.config.pageSupport.PageableQueryEncoder;
import feign.Feign;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig2 {

//    @Bean
//    public ErrorDecoder errorDecoder(){
//        return new ClientErrorDecoder2();
//    }

    @Bean
    public PageableQueryEncoder errorDecoder(ObjectFactory<HttpMessageConverters> messageConverters){
        return new PageableQueryEncoder(new SpringEncoder(messageConverters));
    }

//    @Bean
//    public Feign feignBuilder(){
//        return Feign.builder().build();
//    }

//    @Bean
//    public Logger.Level feignLoggerLevel(){
//        return Logger.Level.FULL;
//    }
}
