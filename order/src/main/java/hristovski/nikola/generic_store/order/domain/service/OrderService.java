package hristovski.nikola.generic_store.order.domain.service;

import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.order.Order;
import hristovski.nikola.common.shared.domain.model.shipping.OrderId;
import hristovski.nikola.common.shared.domain.model.shipping.value.Address;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartItem;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;

import java.util.List;

public interface OrderService {
    void orderReady(OrderId orderId);

    void pickUpOrder(OrderId orderId);

    void orderDelivered(OrderId orderId);

    void createOrder(ApplicationUserId userId, Money price, List<ShoppingCartItem> shoppingCartItems,
                     Address shippingAddress);

    List<Order> getOrdersForUser(ApplicationUserId applicationUserId);

    Integer getOrderStatus(OrderId orderId);

    List<Order> getAllOrders();

    List<Order> searchOrders(String query);
}
