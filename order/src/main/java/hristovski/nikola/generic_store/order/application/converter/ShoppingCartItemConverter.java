package hristovski.nikola.generic_store.order.application.converter;

import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartItem;
import hristovski.nikola.generic_store.order.domain.persistance.entity.OrderItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartItemConverter implements Converter<ShoppingCartItem, OrderItemEntity> {


    @Override
    public OrderItemEntity convert(ShoppingCartItem shoppingCartItem) {
        return new OrderItemEntity( shoppingCartItem.getProductId(),
                shoppingCartItem.getPrice(),
                shoppingCartItem.getQuantity(),
                shoppingCartItem.getProductName());
    }
}
