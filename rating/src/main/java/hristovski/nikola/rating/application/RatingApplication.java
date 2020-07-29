package hristovski.nikola.rating.application;

import hristovski.nikola.rating.spring.KafkaConfiguration;
import hristovski.nikola.rating.spring.RatingConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({RatingConfiguration.class, KafkaConfiguration.class})
@EnableDiscoveryClient
public class RatingApplication {
    public static void main(String[] args) {
        SpringApplication.run(RatingApplication.class, args);
    }
}
