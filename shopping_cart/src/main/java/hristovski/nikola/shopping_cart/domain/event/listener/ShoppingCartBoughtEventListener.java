package hristovski.nikola.shopping_cart.domain.event.listener;

import hristovski.nikola.generic_store.base.domain.DomainEvent;
import hristovski.nikola.generic_store.message.domain.event.ShoppingCartBoughtEvent;
import hristovski.nikola.generic_store.message.domain.event.command.CreateShoppingCartItemCommand;
import hristovski.nikola.shopping_cart.domain.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static hristovski.nikola.common.shared.domain.constants.Group.GROUP_ID_SHOPPING_CART;
import static hristovski.nikola.common.shared.domain.constants.Topic.TOPIC_CREATE_SHOPPING_CART_ITEM;
import static hristovski.nikola.common.shared.domain.constants.Topic.TOPIC_SHOPPING_CART_BOUGHT;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingCartBoughtEventListener {

    private final ShoppingCartService shoppingCartService;

    @KafkaListener(topics = TOPIC_SHOPPING_CART_BOUGHT, groupId = GROUP_ID_SHOPPING_CART)
    public void consumeProductCreatedEvent(String jsonMessage) {
        log.info("Got shopping cart bought event {}", jsonMessage);
        try {
            ShoppingCartBoughtEvent event =
                    DomainEvent.fromJson(jsonMessage, ShoppingCartBoughtEvent.class);

            shoppingCartService.buy(event.getShoppingCartId(), event.getShippingAddress());
        } catch (Exception exception) {
            log.error("Failed to translate the domain event from json string: {}",
                    jsonMessage, exception);
        }
    }
}
