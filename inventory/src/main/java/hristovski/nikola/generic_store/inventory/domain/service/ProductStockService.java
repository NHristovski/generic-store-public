package hristovski.nikola.generic_store.inventory.domain.service;

import hristovski.nikola.common.shared.domain.model.inventory.StockResponseElement;
import hristovski.nikola.common.shared.domain.model.product.ProductId;

import java.util.List;
import java.util.Map;

public interface ProductStockService {
    Map<ProductId, StockResponseElement> findStocks(List<ProductId> products);
}
