package com.example.feign.client.feignClient;

import com.example.feign.client.dto.ExampleDto;
import com.example.feign.client.feignClient.config.ClientErrorDecoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        value = "feign-example-client-fallback",
        path = "/",
//		fallbackFactory = VnfPackageStoreClient.VnfPackageStoreClientFallbackFactory.class,
//		primary = false,
//		decode404 = true,
//        url = "${example.client.api.path}"
        url = "http://localhost:8080",
        fallback = FeignExampleClientApiWithFallback.FeignExampleClientApiFallback.class,
//        fallbackFactory = FeignExampleClientApiFallbackFactory.class
        configuration = FeignExampleClientApiWithFallback.FeignExampleClientApiWithFallbackConfig.class
)
public interface FeignExampleClientApiWithFallback {
    @PostMapping("info")
    String getInfo(@RequestBody ExampleDto exampleDto);

    @GetMapping("proxy")
    String getInfoFromProxy(@RequestBody ExampleDto exampleDto);

    @Slf4j
    @Component
    static class FeignExampleClientApiFallback implements FeignExampleClientApiWithFallback {
        @Override
        public String getInfo(ExampleDto exampleDto) {
            log.debug("fallback called");
            return "fallback called";
        }

        @Override
        public String getInfoFromProxy(ExampleDto exampleDto) {
            log.debug("fallback called");
            return null;
        }
    }

    @Configuration
    static class FeignExampleClientApiWithFallbackConfig {

        @Bean
        public ErrorDecoder errorDecoder(){
            return new ErrorDecoder.Default();
        }

//        @Bean
//        public Encoder encoder(){
//            return new Encoder.Default();
//        }
    }
}
