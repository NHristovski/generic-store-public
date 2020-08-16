package hristovski.nikola.shopping_cart.application.converter;

import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCart;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartItem;
import hristovski.nikola.shopping_cart.domain.model.cart.ShoppingCartEntity;
import hristovski.nikola.shopping_cart.domain.model.cart.ShoppingCartItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingCartConverter implements Converter<ShoppingCartEntity, ShoppingCart> {

    @Override
    public ShoppingCart convert(ShoppingCartEntity shoppingCartEntity) {
        return new ShoppingCart(
                shoppingCartEntity.getVersion(),
                shoppingCartEntity.getId(),
                mapShoppingCartItemEntitiesToShoppingCartItems(
                        shoppingCartEntity.getShoppingCartItemEntities()
                )
        );
    }

    private Set<ShoppingCartItem> mapShoppingCartItemEntitiesToShoppingCartItems(Set<ShoppingCartItemEntity> entities) {
        return entities.stream()
                .map(shoppingCartItemEntity -> new ShoppingCartItem(
                        shoppingCartItemEntity.getId(),
                        shoppingCartItemEntity.getVersion(),
                        shoppingCartItemEntity.getProductId(),
                        shoppingCartItemEntity.getPrice(),
                        shoppingCartItemEntity.getQuantity(),
                        shoppingCartItemEntity.getProductName()
                ))
                .collect(Collectors.toSet());
    }
}
