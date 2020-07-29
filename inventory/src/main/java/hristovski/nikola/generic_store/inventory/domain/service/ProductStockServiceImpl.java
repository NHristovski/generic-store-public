package hristovski.nikola.generic_store.inventory.domain.service;

import hristovski.nikola.common.shared.domain.factory.inventory.StockResponseElementFactory;
import hristovski.nikola.common.shared.domain.model.inventory.StockResponseElement;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.generic_store.inventory.domain.persistance.entity.ProductStockEntity;
import hristovski.nikola.generic_store.inventory.domain.persistance.repository.ProductStockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductStockServiceImpl implements ProductStockService {

    private final ProductStockRepository productStockRepository;

    @Override
    public Map<ProductId, StockResponseElement> findStocks(List<ProductId> products) {
        Map<ProductId, StockResponseElement> result = new HashMap<>();

        for (ProductId productId : products) {
            Optional<ProductStockEntity> stockEntity =
                    productStockRepository.findByProductId(productId);

            if (stockEntity.isPresent()) {
                result.put(productId, StockResponseElementFactory.fromQuantity(
                        stockEntity.get().getStock()
                ));
            } else {
                result.put(productId, StockResponseElementFactory.fromError(
                        "Failed to find stock for product " + productId
                        )
                );
            }
        }

        return result;
    }
}
