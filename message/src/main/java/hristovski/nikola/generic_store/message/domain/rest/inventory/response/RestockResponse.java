package hristovski.nikola.generic_store.message.domain.rest.inventory.response;

import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestockResponse {
    @NotNull
    private ProductId productId;

    @NotNull
    private Quantity stock;
}
