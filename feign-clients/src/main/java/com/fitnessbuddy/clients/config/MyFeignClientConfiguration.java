package com.fitnessbuddy.clients.config;

import com.fitnessbuddy.clients.exceptions.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
/*
 * Configuration class for feign client
 */
public class MyFeignClientConfiguration {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}
