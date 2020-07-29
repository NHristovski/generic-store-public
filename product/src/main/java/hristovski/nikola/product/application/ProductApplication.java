package hristovski.nikola.product.application;

import hristovski.nikola.product.spring.KafkaConfiguration;
import hristovski.nikola.product.spring.ProductConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ProductConfiguration.class, KafkaConfiguration.class})
@EnableDiscoveryClient
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}
