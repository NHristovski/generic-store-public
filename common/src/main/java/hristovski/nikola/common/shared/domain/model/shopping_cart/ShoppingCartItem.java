package hristovski.nikola.common.shared.domain.model.shopping_cart;

import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.all.value.Name;
import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartItem {

    ShoppingCartItemId shoppingCartItemId;

    private Long version;

    private ProductId productId;

    private Money price;

    private Quantity quantity;

    private Name productName;
}
