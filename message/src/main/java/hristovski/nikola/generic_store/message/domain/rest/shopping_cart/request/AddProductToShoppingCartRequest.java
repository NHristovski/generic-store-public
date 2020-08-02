package hristovski.nikola.generic_store.message.domain.rest.shopping_cart.request;

import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.all.value.Name;
import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddProductToShoppingCartRequest {

    @NonNull
    private String productId;

    @NonNull
    private Quantity quantity;

    @NonNull
    private Money price;

    @NonNull
    private Name productName;
}
