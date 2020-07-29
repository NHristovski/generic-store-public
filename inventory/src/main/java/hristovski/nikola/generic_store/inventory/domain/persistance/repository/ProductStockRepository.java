package hristovski.nikola.generic_store.inventory.domain.persistance.repository;

import hristovski.nikola.common.shared.domain.model.inventory.ProductStockId;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.generic_store.inventory.domain.persistance.entity.ProductStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStockEntity, ProductStockId> {
    Optional<ProductStockEntity> findByProductId(ProductId productId);
}
