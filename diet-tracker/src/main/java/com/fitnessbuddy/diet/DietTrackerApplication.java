package com.fitnessbuddy.diet;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.fitnessbuddy.diet",
        })
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.fitnessbuddy.clients"
)
@Slf4j
public class DietTrackerApplication {
    public static void main(String[] args) {
        log.info("Diet service loaded... {}", DietTrackerApplication.class);
        SpringApplication.run(DietTrackerApplication.class, args);
    }
}
