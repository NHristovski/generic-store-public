package hristovski.nikola.common.shared.domain.factory.inventory;

import hristovski.nikola.common.shared.domain.model.all.value.Error;
import hristovski.nikola.common.shared.domain.model.inventory.StockResponseElement;
import hristovski.nikola.common.shared.domain.model.all.value.Quantity;

public class StockResponseElementFactory {

    public static StockResponseElement fromError(String errorMessage) {
        return StockResponseElement.builder()
                .error(new Error(errorMessage, true))
                .build();
    }

    public static StockResponseElement fromQuantity(Quantity quantity) {
        return StockResponseElement.builder()
                .error(new Error(null, false))
                .quantity(quantity)
                .build();
    }
}
