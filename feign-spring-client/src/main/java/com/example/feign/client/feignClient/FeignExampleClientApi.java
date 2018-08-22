package com.example.feign.client.feignClient;

import com.example.feign.client.dto.ExampleDto;
import com.example.feign.client.feignClient.config.ClientConfig2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        value = "feign-example-client",
        path = "/",
//		fallbackFactory = VnfPackageStoreClient.VnfPackageStoreClientFallbackFactory.class,
//		primary = false,
//		decode404 = true,
//        url = "${example.client.api.path}"
        url = "http://localhost:8080"//,
//        fallback = FeignExampleClientApiFallback.class,
//        fallbackFactory = FeignExampleClientApiFallbackFactory.class
//        configuration = ClientConfig2.class
)
public interface FeignExampleClientApi {
    @PostMapping("info")
    public String getInfo(@RequestBody ExampleDto exampleDto);

    @GetMapping("proxy")
    public String getInfoFromProxy(@RequestBody ExampleDto exampleDto);

    @GetMapping("page")
    Page<String> getPageInfo(Pageable pageable);

    @GetMapping("page")
    Page<String> getPageInfo();
}
