package hristovski.nikola.payment.spring;

import com.stripe.Stripe;
import hristovski.nikola.payment.PaymentComponents;
import hristovski.nikola.payment.application.configuration.PaymentProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan(basePackageClasses = {PaymentComponents.class})
//@EnableJpaRepositories(basePackageClasses = {RatingComponents.class})
//@EntityScan(basePackageClasses = {RatingComponents.class})
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

//    try {
//        // Use Stripe's library to make requests...
//    } catch (CardException e) {
//        // Since it's a decline, CardException will be caught
//        System.out.println("Status is: " + e.getCode());
//        System.out.println("Message is: " + e.getMessage());
//    } catch (RateLimitException e) {
//        // Too many requests made to the API too quickly
//    } catch (InvalidRequestException e) {
//        // Invalid parameters were supplied to Stripe's API
//    } catch (AuthenticationException e) {
//        // Authentication with Stripe's API failed
//        // (maybe you changed API keys recently)
//    } catch (APIConnectionException e) {
//        // Network communication with Stripe failed
//    } catch (StripeException e) {
//        // Display a very generic error to the user, and maybe send
//        // yourself an email
//    } catch (Exception e) {
//        // Something else happened, completely unrelated to Stripe
//    }
}


