package hristovski.nikola.users.spring;

import hristovski.nikola.users.UserComponents;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackageClasses = {UserComponents.class})
@EnableJpaRepositories(basePackageClasses = {UserComponents.class})
@EntityScan(basePackageClasses = {UserComponents.class})
@Slf4j
public class UserConfiguration {
    public UserConfiguration() {
        log.info("User configuration initialized.");
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
