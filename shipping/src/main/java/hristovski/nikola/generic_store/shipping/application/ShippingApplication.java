package hristovski.nikola.generic_store.shipping.application;

import hristovski.nikola.generic_store.shipping.spring.ShippingConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ShippingConfiguration.class})
@EnableDiscoveryClient
public class ShippingApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShippingApplication.class, args);
    }
}

