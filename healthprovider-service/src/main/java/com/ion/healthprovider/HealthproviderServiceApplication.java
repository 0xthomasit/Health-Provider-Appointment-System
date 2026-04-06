package com.ion.healthprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HealthproviderServiceApplication {

    static void main(String[] args) {
        SpringApplication.run(HealthproviderServiceApplication.class, args);
    }

}
