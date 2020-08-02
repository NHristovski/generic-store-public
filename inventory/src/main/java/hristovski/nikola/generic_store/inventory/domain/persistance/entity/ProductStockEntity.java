package hristovski.nikola.generic_store.inventory.domain.persistance.entity;

import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import hristovski.nikola.common.shared.domain.model.inventory.ProductStockId;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.validator.Validators;
import hristovski.nikola.generic_store.base.domain.AbstractEntity;
import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_storage")
public class ProductStockEntity extends AbstractEntity<ProductStockId> {
    @Version
    private Long version;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "product_id", nullable = false))
    private ProductId productId;

    @Embedded
    private Quantity stock;

    public ProductStockEntity(ProductId productId, Quantity stock) {
        super(DomainObjectId.randomId(ProductStockId.class));
        this.productId = productId;
        this.stock = stock;
        this.version = 0L;
    }

    public void restock(Quantity newStock) {
        Objects.requireNonNull(newStock);
        Validators.requireNonNegative(newStock.getQuantity());

        this.stock = newStock;
    }

    public void reserve(Long quantity) {
        this.stock = stock.minus(quantity);
    }
}
