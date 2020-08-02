package hristovski.nikola.generic_store.message.domain.rest.inventory.response;

import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckStockResponse {
    private boolean success;
    private Quantity stock;
}
