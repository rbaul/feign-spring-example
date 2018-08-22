package com.example.feign.client.config;

import com.example.feign.client.feignClient.FeignExampleClientApi;
import com.example.feign.client.feignClient.FeignExampleClientApiWithFallback;
import com.example.feign.client.feignClient.FeignExampleClientApiWithFallbackFactory;
import com.example.feign.client.feignClient.config.ClientConfig;
import com.example.feign.client.feignClient.config.ClientErrorDecoder2;
import feign.codec.ErrorDecoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
//@EnableFeignClients(clients = FeignExampleClientApi.class)
@EnableFeignClients(clients = {FeignExampleClientApi.class,
        FeignExampleClientApiWithFallback.class, FeignExampleClientApiWithFallbackFactory.class},
        defaultConfiguration = ClientConfig.class)
public class FeignClientConfig {

}
