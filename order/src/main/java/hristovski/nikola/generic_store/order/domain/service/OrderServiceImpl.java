package hristovski.nikola.generic_store.order.domain.service;

import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.order.Order;
import hristovski.nikola.common.shared.domain.model.shipping.OrderId;
import hristovski.nikola.common.shared.domain.model.shipping.value.Address;
import hristovski.nikola.common.shared.domain.model.shipping.value.ShippingStatus;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartItem;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.order.domain.persistance.entity.OrderEntity;
import hristovski.nikola.generic_store.order.domain.persistance.entity.OrderItemEntity;
import hristovski.nikola.generic_store.order.domain.persistance.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ConversionService conversionService;

    @Override
    public void orderReady(OrderId orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("Order with id " + orderId + " not found.")
        );
        orderEntity.orderReady();

        orderRepository.save(orderEntity);
    }

    @Override
    public void pickUpOrder(OrderId orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("Order with id " + orderId + " not found.")
        );
        orderEntity.pickUpOrder();

        orderRepository.save(orderEntity);
    }

    @Override
    public void orderDelivered(OrderId orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(
                () -> new RuntimeException("Order with id " + orderId + " not found.")
        );
        orderEntity.orderDelivered();

        orderRepository.save(orderEntity);
    }

    @Override
    public void createOrder(ApplicationUserId userId, Money price, List<ShoppingCartItem> shoppingCartItems, Address shippingAddress) {
        OrderEntity orderEntity = new OrderEntity(userId, shippingAddress, price,
                shoppingCartItems.stream()
                        .map(item -> conversionService.convert(item, OrderItemEntity.class))
                        .collect(Collectors.toSet())
        );

        orderRepository.saveAndFlush(orderEntity);
    }

    @Override
    public List<Order> getOrdersForUser(ApplicationUserId applicationUserId) {
        return orderRepository.findAllByUserIdEqualsOrderByCreatedOnDesc(applicationUserId).stream()
                .map(OrderEntity::toOrder)
                .collect(Collectors.toList());
    }

    @Override
    public Integer getOrderStatus(OrderId orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Failed to find order"));

        if (orderEntity.getStatus().equals(ShippingStatus.NOT_READY)) {
            return 0;
        }
        if (orderEntity.getStatus().equals(ShippingStatus.READY)) {
            return 1;
        }
        if (orderEntity.getStatus().equals(ShippingStatus.DELIVERING)) {
            return 2;
        }
        if (orderEntity.getStatus().equals(ShippingStatus.DELIVERED)) {
            return 3;
        }
        return -1;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAllByOrderByCreatedOnDesc().stream()
                .map(OrderEntity::toOrder)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> searchOrders(String query) {
        return orderRepository
                .findAllByIdIdContainsIgnoreCaseOrUserIdIdContainsIgnoreCaseOrderByCreatedOnDesc(query, query)
                .stream()
                .map(OrderEntity::toOrder)
                .collect(Collectors.toList());
    }
}
