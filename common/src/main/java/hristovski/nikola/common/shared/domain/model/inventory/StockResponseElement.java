package hristovski.nikola.common.shared.domain.model.inventory;

import hristovski.nikola.common.shared.domain.model.all.value.Error;
import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockResponseElement {
    private Error error;
    private Quantity quantity;
}
