package hristovski.nikola.generic_store.inventory.spring;

import hristovski.nikola.generic_store.inventory.InventoryComponents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackageClasses = {InventoryComponents.class})
@EnableJpaRepositories(basePackageClasses = {InventoryComponents.class})
@EntityScan(basePackageClasses = {InventoryComponents.class})
@Slf4j
public class InventoryConfiguration {
    public InventoryConfiguration() {
        log.info("Inventory configuration initialized.");
    }
}


