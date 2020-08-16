package hristovski.nikola.payment.spring;

import com.stripe.Stripe;
import hristovski.nikola.payment.PaymentComponents;
import hristovski.nikola.payment.application.configuration.PaymentProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan(basePackageClasses = {PaymentComponents.class})
@Slf4j
public class PaymentConfiguration {

    private final PaymentProperties paymentProperties;

    public PaymentConfiguration(PaymentProperties paymentProperties) {
        this.paymentProperties = paymentProperties;
        log.info("Payment configuration initialized.");
    }

    @PostConstruct
    public void initializeString() {
        Stripe.apiKey = paymentProperties.getSecretKey();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}


