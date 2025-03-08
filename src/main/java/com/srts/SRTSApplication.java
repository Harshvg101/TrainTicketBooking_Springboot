package com.srts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.srts"}) // Manually scan controllers
public class SRTSApplication {
    public static void main(String[] args) {
        SpringApplication.run(SRTSApplication.class, args);
    }
}