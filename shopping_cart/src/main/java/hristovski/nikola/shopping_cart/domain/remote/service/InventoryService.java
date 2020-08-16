package hristovski.nikola.shopping_cart.domain.remote.service;

import hristovski.nikola.common.shared.domain.exception.RestRequestException;
import hristovski.nikola.common.shared.domain.model.inventory.StockResponseElement;
import hristovski.nikola.common.shared.domain.model.product.ProductId;

import java.util.List;
import java.util.Map;

public interface InventoryService {
    boolean decrementStock(ProductId productId) throws RestRequestException;
}
