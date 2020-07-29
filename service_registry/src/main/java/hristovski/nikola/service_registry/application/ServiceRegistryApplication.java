package hristovski.nikola.service_registry.application;

import hristovski.nikola.service_registry.spring.ServiceRegistryConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Import;

@EnableEurekaServer
@SpringBootApplication
@Import({ServiceRegistryConfiguration.class})
public class ServiceRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRegistryApplication.class, args);
    }
}
