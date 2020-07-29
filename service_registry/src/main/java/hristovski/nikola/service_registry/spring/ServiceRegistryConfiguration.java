package hristovski.nikola.service_registry.spring;

import hristovski.nikola.service_registry.ServiceRegistryComponents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@ComponentScan(basePackageClasses = {ServiceRegistryComponents.class})
public class ServiceRegistryConfiguration {
    public ServiceRegistryConfiguration(){
        log.info("Service Registry configuration initialized.");
    }
}
