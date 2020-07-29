package hristovski.nikola.generic_store.config_server.spring;

import hristovski.nikola.generic_store.config_server.ConfigServerComponents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ConfigServerComponents.class)
@Slf4j
public class ConfigServerConfiguration {
    public ConfigServerConfiguration() {
        log.info("Config Server configuration initialized.");
    }
}
