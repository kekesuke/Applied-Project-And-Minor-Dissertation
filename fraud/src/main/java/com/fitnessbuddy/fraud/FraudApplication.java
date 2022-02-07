package com.fitnessbuddy.fraud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FraudApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(FraudApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(FraudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
	


}
