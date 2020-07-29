package hristovski.nikola.shopping_cart.domain.model;

import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartItemId;
import hristovski.nikola.generic_store.base.domain.AbstractEntity;
import hristovski.nikola.shopping_cart.application.port.exception.MaxQuantityReachedException;
import hristovski.nikola.shopping_cart.application.port.exception.MinQuantityReachedException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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

//    @Embedded
//    private ShoppingCartItemStatus status;

    public void incrementQuantity(int value) throws MaxQuantityReachedException {
        // TODO send event.
//        Response r = amqpTemplate.convertSendAndReceiv(newQuantity);
//        if (r.ok){
//            // inc quantity in db
//            return ResponseEntity.ok()
//        }else{
//            return ResponseEntity.badRequest()
//        }
        this.quantity = quantity.plus(value);
    }

    public void decrementQuantity(int value) throws MinQuantityReachedException {
        this.quantity = quantity.minus(value);
    }

    public Money totalPrice() {
        return price.times(quantity.getQuantity());
    }
}
