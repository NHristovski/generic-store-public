package hristovski.nikola.generic_store.auth.spring;

import hristovski.nikola.generic_store.auth.AuthComponents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackageClasses = AuthComponents.class)
@Slf4j
public class AuthConfiguration {

    public AuthConfiguration() {
        log.info("Auth configuration initialized.");
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }
}
