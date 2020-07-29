package hristovski.nikola.shopping_cart.application.converter;

import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartItem;
import hristovski.nikola.shopping_cart.domain.model.ShoppingCartItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartItemConverter implements Converter<ShoppingCartItemEntity, ShoppingCartItem> {
    @Override
    public ShoppingCartItem convert(ShoppingCartItemEntity shoppingCartItemEntity) {
        return new ShoppingCartItem(
                shoppingCartItemEntity.getVersion(),
                shoppingCartItemEntity.getProductId(),
                shoppingCartItemEntity.getPrice(),
                shoppingCartItemEntity.getQuantity()
        );
    }
}
