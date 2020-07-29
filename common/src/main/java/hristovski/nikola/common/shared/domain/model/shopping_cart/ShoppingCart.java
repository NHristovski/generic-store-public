package hristovski.nikola.common.shared.domain.model.shopping_cart;

import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
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

    //TODO Change username to userId in every project ( API GATEWAY )
    private ApplicationUserId userId;

    private Set<ShoppingCartItem> shoppingCartItems;
}
