package hristovski.nikola.generic_store.order.application;

import hristovski.nikola.generic_store.order.spring.KafkaConfiguration;
import hristovski.nikola.generic_store.order.spring.OrderConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({OrderConfiguration.class, KafkaConfiguration.class})
@EnableDiscoveryClient
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}

