package hristovski.nikola.shopping_cart.domain.persistence.entity;

import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.all.value.Name;
import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartItemId;
import hristovski.nikola.generic_store.base.domain.AbstractEntity;
import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Slf4j
@Table(name = "shopping_cart_item")
public class ShoppingCartItemEntity extends AbstractEntity<ShoppingCartItemId> {

    @Version
    private Long version;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "product_id", nullable = false))
    private ProductId productId;

    @Embedded
    private Money price;

    @Embedded
    private Quantity quantity;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "name", nullable = false))
    })
    private Name productName;

    public ShoppingCartItemEntity(ProductId productId, Money price, Quantity quantity, Name productName) {
        super(DomainObjectId.randomId(ShoppingCartItemId.class));
        this.version = 0L;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.productName = productName;
    }

    public void incrementQuantity(Long value) {
        this.quantity = quantity.plus(value);
    }

    public void decrementQuantity(Long value) {
        this.quantity = quantity.minus(value);
    }

    public Money totalPrice() {
        return price.times(quantity.getQuantity());
    }
}
