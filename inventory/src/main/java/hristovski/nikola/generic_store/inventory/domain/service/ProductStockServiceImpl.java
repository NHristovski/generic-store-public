package hristovski.nikola.generic_store.inventory.domain.service;

import hristovski.nikola.common.shared.domain.factory.inventory.StockResponseElementFactory;
import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import hristovski.nikola.common.shared.domain.model.inventory.StockResponseElement;
import hristovski.nikola.common.shared.domain.model.inventory.value.ProductReservation;
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

    @Override
    public ProductStockEntity createProductStock(ProductId productId, Quantity quantity) {
        return productStockRepository.saveAndFlush(
                new ProductStockEntity(
                        productId, quantity
                )
        );
    }

    @Override
    public void restock(ProductId id, Quantity stock) {
        ProductStockEntity productStockEntity =
                productStockRepository.findByProductId(id).orElseThrow(RuntimeException::new);

        productStockEntity.restock(stock);

        productStockRepository.saveAndFlush(productStockEntity);
    }

    @Override
    public ProductStockEntity findByProductId(ProductId productId) {
        return productStockRepository.findByProductId(productId).orElseThrow(RuntimeException::new);
    }

    @Override
    public boolean updateStock(ProductId productId, Long quantity) {
        ProductStockEntity productStockEntity = productStockRepository.findByProductId(productId).orElseThrow(RuntimeException::new);

        if (productStockEntity.getStock().getQuantity() < quantity) {
            return false;
        }

        productStockEntity.reserve(quantity);

        productStockRepository.saveAndFlush(productStockEntity);

        return true;
    }

    @Override
    public Boolean decrementStock(ProductId productId) {
        try {
            ProductStockEntity productStockEntity = productStockRepository.findByProductId(productId)
                    .orElseThrow(RuntimeException::new);

            productStockEntity.decrementStock();

            productStockRepository.saveAndFlush(productStockEntity);

            return true;
        } catch (Exception ex) {
            log.error("Failed to decrement product stock", ex);
            return false;
        }
    }

    @Override
    public void freeProductsReservation(List<ProductReservation> productReservations) {
        productReservations.forEach(reservation -> {
            ProductStockEntity productStockEntity = productStockRepository.findByProductId(
                    reservation.getProductId()
            ).orElseThrow(RuntimeException::new);

            productStockEntity.addStock(reservation.getQuantity());
            productStockRepository.saveAndFlush(productStockEntity);
        });
    }
}
