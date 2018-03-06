package com.teryy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TokenApplication {

	public static void main(String[] args) {
        SpringApplication.run(TokenApplication.class, args);
    }
}
