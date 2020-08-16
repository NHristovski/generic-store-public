package hristovski.nikola.payment.application;

import hristovski.nikola.payment.spring.KafkaConfiguration;
import hristovski.nikola.payment.spring.PaymentConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({PaymentConfiguration.class, KafkaConfiguration.class})
@EnableDiscoveryClient
public class PaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }
}
