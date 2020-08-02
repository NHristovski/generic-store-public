package hristovski.nikola.product.domain.remote.service;

import hristovski.nikola.common.shared.domain.exception.RestRequestException;
import hristovski.nikola.common.shared.domain.model.inventory.StockResponseElement;
import hristovski.nikola.common.shared.domain.model.product.ProductId;

import java.util.List;
import java.util.Map;

public interface InventoryService {
    Map<ProductId, StockResponseElement> getProductStocks(List<ProductId> productIds) throws RestRequestException;
}
