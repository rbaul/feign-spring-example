package com.example.feign.client.feignClient;

import com.example.feign.client.dto.ExampleDto;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        value = "feign-example-client-fallback-factory",
        path = "/",
//		fallbackFactory = VnfPackageStoreClient.VnfPackageStoreClientFallbackFactory.class,
//		primary = false,
//		decode404 = true,
//        url = "${example.client.api.path}"
        url = "http://localhost:8080",
//        fallback = FeignExampleClientApiFallback.class,
        fallbackFactory = FeignExampleClientApiWithFallbackFactory.FeignExampleClientApiFallbackFactory.class,
//        configuration = ClientConfig2.class
        configuration = FeignExampleClientApiWithFallbackFactory.FeignExampleClientApiWithFallbackConfig.class
)
public interface FeignExampleClientApiWithFallbackFactory {
    @PostMapping("info")
    String getInfo(@RequestBody ExampleDto exampleDto);

    @GetMapping("proxy")
    String getInfoFromProxy(@RequestBody ExampleDto exampleDto);

    @Slf4j
    @Component
    class FeignExampleClientApiFallbackFactory implements FallbackFactory<FeignExampleClientApiWithFallbackFactory> {
        @Override
        public FeignExampleClientApiWithFallbackFactory create(Throwable cause) {

            log.error(cause.getMessage(), cause);
            return new FeignExampleClientApiWithFallbackFactory() {
                @Override
                public String getInfo(ExampleDto exampleDto) {
                    log.error(exampleDto.toString());
                    return null;
                }

                @Override
                public String getInfoFromProxy(ExampleDto exampleDto) {
                    return null;
                }
            };
        }
    }

    @Configuration
    static class FeignExampleClientApiWithFallbackConfig {

        @Bean
        public ErrorDecoder errorDecoder(){
            return new ErrorDecoder.Default();
        }

        @Bean
        public Encoder encoder(){
            return new Encoder.Default();
        }
    }
}
