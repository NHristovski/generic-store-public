package hristovski.nikola.shopping_cart.spring;

import hristovski.nikola.shopping_cart.ShoppingCartComponents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
@ComponentScan(basePackageClasses = {ShoppingCartComponents.class})
@EnableJpaRepositories(basePackageClasses = {ShoppingCartComponents.class})
@EntityScan(basePackageClasses = {ShoppingCartComponents.class})
@EnableScheduling
public class ShoppingCartConfiguration {
    public ShoppingCartConfiguration() {
        log.info("Shopping Card Initialized");
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }
}
