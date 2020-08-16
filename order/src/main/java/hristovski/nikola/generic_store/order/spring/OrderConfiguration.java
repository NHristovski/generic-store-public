package hristovski.nikola.generic_store.order.spring;

import hristovski.nikola.generic_store.order.OrderComponents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackageClasses = {OrderComponents.class})
@EnableJpaRepositories(basePackageClasses = {OrderComponents.class})
@EntityScan(basePackageClasses = {OrderComponents.class})
@Slf4j
public class OrderConfiguration {
    public OrderConfiguration() {
        log.info("Shipping configuration initialized.");
    }
}






