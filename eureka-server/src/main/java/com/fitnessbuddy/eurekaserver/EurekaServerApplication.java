package com.fitnessbuddy.eurekaserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@Slf4j
public class  EurekaServerApplication {

    public static void main(String[] args) {
        log.info("Eureka-server", EurekaServerApplication.class);
        SpringApplication.run(EurekaServerApplication.class, args);
    }

}
