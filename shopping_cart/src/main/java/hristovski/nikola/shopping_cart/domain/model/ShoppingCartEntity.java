package hristovski.nikola.shopping_cart.domain.model;

import hristovski.nikola.common.shared.domain.model.all.value.Currency;
import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartId;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.base.domain.AbstractEntity;
import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import lombok.Data;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "shopping_cart")
@Data
public class ShoppingCartEntity extends AbstractEntity<ShoppingCartId> {

    @Version
    private Long version;

    //TODO Change username to userId in every project ( API GATEWAY )
    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false))
    private ApplicationUserId applicationUserId;

    // TODO TEST THAT empting the shopping cart does not delete shoppign cart item entities
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ShoppingCartItemEntity> shoppingCartItemEntities;

    private Instant lastUpdateTimestamp;

    public Money totalPrice() {
        double amount = shoppingCartItemEntities.stream()
                .map(ShoppingCartItemEntity::totalPrice)
                .mapToDouble(Money::getAmount)
                .sum();

        if (amount == 0d) {
            return new Money();
        } else {
            // at least one item in the shopping cart items
            Currency currency = shoppingCartItemEntities.iterator()
                    .next()
                    .getPrice()
                    .getCurrency();
            return new Money(currency, amount);
        }
    }

    // TODO pay clicked should refresh lastUpdateTimestamp
    // TODO CREATE SCHEDULER
//    public List<ShoppingCartItems> clear(){
//        // TODO delete all shopping cart items, fire event FreeProductsStockReservations
    // clear list
    // after this method call save
//    }

    public void addShoppingCartItem(ShoppingCartItemEntity item) {

        Objects.requireNonNull(item);

        Optional<ShoppingCartItemEntity> shoppingCartItemEntity = this.shoppingCartItemEntities
                .stream()
                .filter(shoppingCartItem ->
                        shoppingCartItem.getProductId().getId().equals(item.getProductId().getId())
                )
                .findFirst();

        if (shoppingCartItemEntity.isPresent()) {
            shoppingCartItemEntity.get()
                    .incrementQuantity(item.getQuantity().getQuantity());
        } else {
            this.shoppingCartItemEntities.add(item);
        }

        refreshLastUpdate();
    }

    public ShoppingCartEntity() {
        super(DomainObjectId.randomId(ShoppingCartId.class));
        version = 0L;
        applicationUserId = null;
        this.lastUpdateTimestamp = Instant.now();
        shoppingCartItemEntities = new HashSet<>();
    }

    public ShoppingCartEntity(ApplicationUserId applicationUserId) {
        super(DomainObjectId.randomId(ShoppingCartId.class));
        version = 0L;
        this.applicationUserId = applicationUserId;
        this.lastUpdateTimestamp = Instant.now();
        shoppingCartItemEntities = new HashSet<>();
    }

    public void refreshLastUpdate() {
        this.lastUpdateTimestamp = Instant.now();
    }
}
