package hristovski.nikola.rating.spring;

import hristovski.nikola.rating.RatingComponents;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackageClasses = {RatingComponents.class})
@EnableJpaRepositories(basePackageClasses = {RatingComponents.class})
@EntityScan(basePackageClasses = {RatingComponents.class})
@Slf4j
public class RatingConfiguration {
    public RatingConfiguration() {
        log.info("Rating configuration initialized.");
    }
}
