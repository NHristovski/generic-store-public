package hristovski.nikola.common.shared.domain.model.shopping_cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {
    private Long version;
    private ShoppingCartId shoppingCartId;
    private Set<ShoppingCartItem> shoppingCartItems;
}
