package hristovski.nikola.generic_store.message.domain.rest.inventory.request;


import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.all.value.Name;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CheckStockRequest {
    @NotNull
    private ProductId productId;

    @NotNull
    private Long stock;

    @NotNull
    private Money price;

    @NotNull
    private Name productName;
}
