package hristovski.nikola.common.shared.domain.model.order;

import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.all.value.Name;
import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.shipping.OrderItemId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private OrderItemId orderItemId;
    private Long version;
    private ProductId productId;
    private Money price;
    private Quantity quantity;
    private Name productName;
}
