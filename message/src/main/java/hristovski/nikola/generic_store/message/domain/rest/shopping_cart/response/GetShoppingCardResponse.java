package hristovski.nikola.generic_store.message.domain.rest.shopping_cart.response;

import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetShoppingCardResponse {
    @NotNull
    ShoppingCart shoppingCart;
}
