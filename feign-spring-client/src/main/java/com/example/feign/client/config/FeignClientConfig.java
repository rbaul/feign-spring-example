package com.example.feign.client.config;

import com.example.feign.client.feignClient.FeignExampleClientApi;
import com.example.feign.client.feignClient.FeignExampleClientApiWithFallback;
import com.example.feign.client.feignClient.FeignExampleClientApiWithFallbackFactory;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableFeignClients(clients = FeignExampleClientApi.class)
@EnableFeignClients(clients = {FeignExampleClientApi.class,
        FeignExampleClientApiWithFallback.class, FeignExampleClientApiWithFallbackFactory.class})
public class FeignClientConfig {
}
