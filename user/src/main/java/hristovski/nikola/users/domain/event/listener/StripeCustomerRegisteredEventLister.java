package hristovski.nikola.users.domain.event.listener;

import hristovski.nikola.generic_store.base.domain.DomainEvent;
import hristovski.nikola.generic_store.message.domain.event.StripeCustomerRegisteredEvent;
import hristovski.nikola.generic_store.message.domain.rest.user.response.GetApplicationUserResponse;
import hristovski.nikola.users.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static hristovski.nikola.common.shared.domain.constants.Group.GROUP_ID_USER;
import static hristovski.nikola.common.shared.domain.constants.Topic.TOPIC_STRIPE_CUSTOMER_CREATED;

@Service
@RequiredArgsConstructor
@Slf4j
public class StripeCustomerRegisteredEventLister {

    private final UserService userService;

    @KafkaListener(topics = TOPIC_STRIPE_CUSTOMER_CREATED, groupId = GROUP_ID_USER)
    public void consumeStripeCustomerRegisteredEvent(String jsonMessage) {
        log.info("Got stripeCustomerRegisteredEvent {}", jsonMessage);
        try {
            StripeCustomerRegisteredEvent event =
                    DomainEvent.fromJson(jsonMessage, StripeCustomerRegisteredEvent.class);

            userService.updateCustomerId(event.getApplicationUserId(), event.getCustomerId());

        } catch (Exception exception) {
            log.error("Failed to translate the domain event from json string: {}",
                    jsonMessage, exception);
        }
    }
}
