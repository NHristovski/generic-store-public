package hristovski.nikola.shopping_cart.domain.model.cart;

import hristovski.nikola.common.shared.domain.model.all.value.Currency;
import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.inventory.value.ProductReservation;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartId;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.base.domain.AbstractEntity;
import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

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
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static hristovski.nikola.common.shared.domain.model.all.value.ExchangeRates.exchangeRates;

@Entity
@Table(name = "shopping_cart")
@Data
@Slf4j
public class ShoppingCartEntity extends AbstractEntity<ShoppingCartId> {

    @Version
    private Long version;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false))
    private ApplicationUserId applicationUserId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ShoppingCartItemEntity> shoppingCartItemEntities;

    private Instant lastUpdateTimestamp;

    public Money totalPrice() {
        double amount = shoppingCartItemEntities.stream()
                .map(ShoppingCartItemEntity::totalPrice)
                .mapToDouble(
                        money -> money.getAmount() * exchangeRates.get(money.getCurrency()).get(Currency.MKD))
                .sum();

        if (amount == 0d) {
            return new Money();
        } else {
            // at least one item in the shopping cart items
            return new Money(Currency.MKD, amount);
        }
    }


    public List<ProductReservation> clearSaleItems() {
        List<ProductReservation> productReservations = this.shoppingCartItemEntities
                .stream()
                .map(item -> new ProductReservation(item.getProductId(), item.getQuantity()))
                .collect(Collectors.toList());

        this.shoppingCartItemEntities = new HashSet<>();

        return productReservations;
    }

    public void clear() {
        this.shoppingCartItemEntities = new HashSet<>();
    }

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
