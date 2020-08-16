package hristovski.nikola.common.shared.domain.model.order;

import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.shipping.OrderId;
import hristovski.nikola.common.shared.domain.model.shipping.value.Address;
import hristovski.nikola.common.shared.domain.model.shipping.value.ShippingStatus;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private OrderId orderId;
    private Long version;
    private Instant createdOn;
    private ShippingStatus status;
    private ApplicationUserId userId;
    private Money price;
    private Set<OrderItem> orderItems;
    private Address shippingAddress;
}
