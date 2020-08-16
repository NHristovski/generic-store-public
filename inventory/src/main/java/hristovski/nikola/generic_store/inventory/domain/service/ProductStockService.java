package hristovski.nikola.generic_store.inventory.domain.service;

import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import hristovski.nikola.common.shared.domain.model.inventory.StockResponseElement;
import hristovski.nikola.common.shared.domain.model.inventory.value.ProductReservation;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.generic_store.inventory.domain.persistance.entity.ProductStockEntity;

import java.util.List;
import java.util.Map;

public interface ProductStockService {
    Map<ProductId, StockResponseElement> findStocks(List<ProductId> products);

    ProductStockEntity createProductStock(ProductId productId, Quantity quantity);

    void restock(ProductId id, Quantity stock);

    ProductStockEntity findByProductId(ProductId productId);

    boolean updateStock(ProductId productId, Long stock);

    Boolean decrementStock(ProductId productId);

    void freeProductsReservation(List<ProductReservation> productReservations);
}
