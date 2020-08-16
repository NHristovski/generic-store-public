package hristovski.nikola.shopping_cart.domain.service;

import hristovski.nikola.common.shared.domain.exception.RestRequestException;
import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.all.value.Name;
import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.shipping.value.Address;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCart;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartId;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartItem;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartItemId;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.shopping_cart.application.port.exception.InsufficientQuantityException;
import hristovski.nikola.shopping_cart.application.port.exception.MaxQuantityReachedException;
import hristovski.nikola.shopping_cart.application.port.exception.MinQuantityReachedException;

public interface ShoppingCartService {

    ShoppingCart getShoppingCart(ShoppingCartId shoppingCartId);

    ShoppingCart getShoppingCart(ApplicationUserId userId);

    void deleteShoppingCart(ShoppingCartId shoppingCartId);

    void addProductToShoppingCart(ProductId productId,
                                  ApplicationUserId userId,
                                  Quantity quantity,
                                  Money price,
                                  Name productName) throws InsufficientQuantityException;

    Money getShoppingCartPrice(ShoppingCartId shoppingCartId);

    ShoppingCartItem getShoppingCardItem(ShoppingCartId shoppingCartId, ShoppingCartItemId shoppingCartItemId);

    void deleteShoppingCardItem(ShoppingCartId shoppingCartId, ShoppingCartItemId shoppingCartItemId);

    void incrementQuantity(ShoppingCartId shoppingCartId, ShoppingCartItemId id)
            throws MaxQuantityReachedException, RestRequestException;

    void decrementQuantity(ShoppingCartId shoppingCartId, ShoppingCartItemId id) throws MinQuantityReachedException;

    void buy(ShoppingCartId shoppingCartId, Address shippingAddress);

    void clearUnusedShoppingCarts();

}
