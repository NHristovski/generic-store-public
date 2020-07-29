package hristovski.nikola.product.spring;

import hristovski.nikola.product.ProductComponents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackageClasses = {ProductComponents.class})
@EnableJpaRepositories(basePackageClasses = {ProductComponents.class})
@EntityScan(basePackageClasses = {ProductComponents.class})
@Slf4j
public class ProductConfiguration {
    public ProductConfiguration() {
        log.info("Product configuration initialized.");
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

}
