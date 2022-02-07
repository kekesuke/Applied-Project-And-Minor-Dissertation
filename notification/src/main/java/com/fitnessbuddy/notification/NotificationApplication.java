package com.fitnessbuddy.notification;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.fitnessbuddy.clients"
)
@Slf4j
public class NotificationApplication {
    public static void main(String[] args) {
        log.info("Notification service loaded... {}", NotificationApplication.class);
        SpringApplication.run(NotificationApplication.class, args);
    }
}
