package com.example.feign.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackageClasses = FeignSpringServerApplication.class)
@SpringBootApplication
public class FeignSpringServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignSpringServerApplication.class, args);
    }
}
