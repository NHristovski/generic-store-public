package hristovski.nikola.shopping_cart.domain.model;

import hristovski.nikola.generic_store.base.domain.AbstractEntity;
import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartId;
import hristovski.nikola.common.shared.domain.model.all.value.Currency;
import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
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
import java.util.HashSet;
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
    private ApplicationUserId userId;

    // TODO TEST THAT empting the shopping cart does not delete shoppign cart item entities
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<ShoppingCartItemEntity> shoppingCartItemEntities;

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

    public void addShoppingCartItem(ShoppingCartItemEntity item) {
        this.shoppingCartItemEntities.add(item);
    }

    public ShoppingCartEntity() {
        super(new ShoppingCartId(DomainObjectId.randomId(ShoppingCartId.class).toString()));
        version = 0L;
        userId = null;
        shoppingCartItemEntities = new HashSet<>();
    }
}
