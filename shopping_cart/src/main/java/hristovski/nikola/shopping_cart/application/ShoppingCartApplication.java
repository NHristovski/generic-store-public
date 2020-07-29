package hristovski.nikola.shopping_cart.application;

import hristovski.nikola.shopping_cart.spring.ShoppingCartConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ShoppingCartConfiguration.class})
@EnableDiscoveryClient
public class ShoppingCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartApplication.class, args);
    }

}
