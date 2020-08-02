package hristovski.nikola.common.shared.domain.model.inventory;

import hristovski.nikola.common.shared.domain.model.all.value.Error;
import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StockResponseElement {
    private Error error;
    private Quantity quantity;

    public boolean hasError() {
        return this.error.isHasError();
    }
}
