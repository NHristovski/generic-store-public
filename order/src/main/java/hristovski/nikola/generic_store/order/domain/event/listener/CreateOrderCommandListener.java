package hristovski.nikola.generic_store.order.domain.event.listener;

import hristovski.nikola.generic_store.base.domain.DomainEvent;
import hristovski.nikola.generic_store.message.domain.event.command.CreateOrderCommand;
import hristovski.nikola.generic_store.order.domain.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static hristovski.nikola.common.shared.domain.constants.Group.GROUP_ID_ORDER;
import static hristovski.nikola.common.shared.domain.constants.Topic.TOPIC_CREATE_ORDER;


@Service
@RequiredArgsConstructor
@Slf4j
public class CreateOrderCommandListener {

    private final OrderService orderService;

    @KafkaListener(topics = TOPIC_CREATE_ORDER, groupId = GROUP_ID_ORDER)
    public void consumeCreateOrderCommand(String jsonMessage) {
        log.info("Got create order command {}", jsonMessage);
        try {
            CreateOrderCommand event =
                    DomainEvent.fromJson(jsonMessage, CreateOrderCommand.class);

            orderService.createOrder(
                    event.getApplicationUserId(),
                    event.getPrice(),
                    event.getShoppingCartItems(),
                    event.getAddress()
            );

        } catch (Exception exception) {
            log.error("Failed to translate the domain event from json string: {}",
                    jsonMessage, exception);
        }
    }
}
