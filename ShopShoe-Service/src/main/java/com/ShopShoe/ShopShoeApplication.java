package com.ShopShoe;

import com.ShopShoe.entity.DocumentEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(DocumentEntity.class)
public class ShopShoeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopShoeApplication.class, args);
    }
}
