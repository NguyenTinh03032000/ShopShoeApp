package com.ShopShoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
@EnableDiscoveryClient
public class ShopShoeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopShoeApplication.class, args);
    }
}
