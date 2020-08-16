package hristovski.nikola.generic_store.order.domain.persistance.entity;

import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.all.value.Name;
import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import hristovski.nikola.common.shared.domain.model.order.OrderItem;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.shipping.OrderItemId;
import hristovski.nikola.generic_store.base.domain.AbstractEntity;
import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@Table(name = "order_item")
public class OrderItemEntity extends AbstractEntity<OrderItemId> {
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

    public OrderItemEntity(ProductId productId, Money price, Quantity quantity, Name productName) {
        super(DomainObjectId.randomId(OrderItemId.class));
        this.version = 0L;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
        this.productName = productName;
    }

    public Money totalPrice() {
        return price.times(quantity.getQuantity());
    }

    public OrderItem toOrderItem(){
        return new OrderItem(this.getId(),this.version
        ,this.productId,this.price,this.quantity,this.productName);
    }
}
