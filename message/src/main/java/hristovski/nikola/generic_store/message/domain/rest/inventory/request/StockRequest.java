package hristovski.nikola.generic_store.message.domain.rest.inventory.request;

import hristovski.nikola.common.shared.domain.model.product.ProductId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StockRequest {

    @NotNull
    private List<ProductId> products;
}
