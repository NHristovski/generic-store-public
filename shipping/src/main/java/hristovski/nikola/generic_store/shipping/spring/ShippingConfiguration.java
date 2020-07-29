package hristovski.nikola.generic_store.shipping.spring;

import hristovski.nikola.generic_store.shipping.ShippingComponents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackageClasses = {ShippingComponents.class})
@EnableJpaRepositories(basePackageClasses = {ShippingComponents.class})
@EntityScan(basePackageClasses = {ShippingComponents.class})
@Slf4j
public class ShippingConfiguration {
    public ShippingConfiguration() {
        log.info("Shipping configuration initialized.");
    }
}






