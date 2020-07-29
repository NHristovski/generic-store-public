package hristovski.nikola.generic_store.api_gateway.spring;

import hristovski.nikola.generic_store.api_gateway.ApiGatewayComponents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ApiGatewayComponents.class)
@Slf4j
public class ApiGatewayConfiguration {
    public ApiGatewayConfiguration() {
        log.info("Auth configuration initialized.");
    }
}
