package com.example.feign.server.web.controllers;

import com.example.feign.client.dto.ExampleDto;
import com.example.feign.client.feignClient.FeignExampleClientApi;
import com.example.feign.client.feignClient.FeignExampleClientApiWithFallback;
import com.example.feign.client.feignClient.FeignExampleClientApiWithFallbackFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FeignExampleController {

    private final FeignExampleClientApi feignExampleClientApi;

    private final FeignExampleClientApiWithFallback feignExampleClientApiWithFallback;

    private final FeignExampleClientApiWithFallbackFactory feignExampleClientApiWithFallbackFactory;

    @PostMapping("info")
    public String getInfo(@Validated @RequestBody ExampleDto exampleDto){
        return exampleDto.getName();
    }

    @GetMapping("page")
    public Page<String> getPageInfo(Pageable pageable){
        return new PageImpl<>(Arrays.asList("R", "A"), pageable, pageable.getPageSize());
    }

    @GetMapping("proxy-page")
    public  Page<String> getPageInfoFromProxy(){
        Pageable pageable = PageRequest.of(1, 2);
        return feignExampleClientApi.getPageInfo(pageable);
//        return feignExampleClientApi.getPageInfo();
    }

    @GetMapping("proxy")
    public String getInfoFromProxy(){
        return feignExampleClientApi.getInfo(ExampleDto.builder().build());
    }

    @GetMapping("proxy-fallback")
    public String getInfoFromProxyWithFalback(){
        return feignExampleClientApiWithFallback.getInfo(ExampleDto.builder().build());
    }

    @GetMapping("proxy-fallback-factory")
    public String getInfoFromProxyWithFallbackFactory(){
        return feignExampleClientApiWithFallbackFactory.getInfo(ExampleDto.builder().build());
    }

}
