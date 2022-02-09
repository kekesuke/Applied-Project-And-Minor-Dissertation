package com.fitnessbuddy.notification;


import com.fitnessbuddy.amqp.RabbitMQMessageProducer;
import com.fitnessbuddy.amqp.config.RabbitMQConfig;
import com.fitnessbuddy.notification.configs.NotificationConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {
                "com.fitnessbuddy.notification",
                "com.fitnessbuddy.amqp",
        })
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
