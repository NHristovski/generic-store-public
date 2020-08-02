package hristovski.nikola.shopping_cart.domain.service;

import hristovski.nikola.common.shared.domain.model.all.value.Name;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCart;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartId;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartItem;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartItemId;
import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.message.domain.rest.shopping_cart.request.BuyRequest;
import hristovski.nikola.shopping_cart.application.port.exception.FailedToBuyException;
import hristovski.nikola.shopping_cart.application.port.exception.InsufficientQuantityException;
import hristovski.nikola.shopping_cart.application.port.exception.MaxQuantityReachedException;
import hristovski.nikola.shopping_cart.application.port.exception.MinQuantityReachedException;

public interface ShoppingCartService {

    ShoppingCart getShoppingCart(ShoppingCartId shoppingCartId);

    ShoppingCart getShoppingCart(ApplicationUserId userId);

    // TODO READ IMPLEMENTETITON TO KNOW WHY IT IS COMMENTED
//    ShoppingCart getShoppingCartItems(ApplicationUserId userId);

    //ShoppingCart addShoppingCart(ShoppingCartEntity shoppingCartEntity);

    //void editShoppingCart(ShoppingCartEntity shoppingCartEntity);

    void deleteShoppingCart(ShoppingCartId shoppingCartId);

    void addProductToShoppingCart(ProductId productId,
                                  ApplicationUserId userId,
                                  Quantity quantity,
                                  Money price,
                                  Name productName) throws InsufficientQuantityException;

    Money getShoppingCartPrice(ShoppingCartId shoppingCartId);

    ShoppingCart getShoppingCartHistory(ApplicationUserId userId);

    ShoppingCartItem getShoppingCardItem(ShoppingCartId shoppingCartId, ShoppingCartItemId shoppingCartItemId);

//    ShoppingCartItem addShoppingCardItem(ShoppingCartId shoppingCartId, ShoppingCartItem shoppingCartItem);
//
//    ShoppingCartItem editShoppingCardItem(ShoppingCartId shoppingCartId, ShoppingCartItem shoppingCartItem);

    void deleteShoppingCardItem(ShoppingCartId shoppingCartId, ShoppingCartItemId shoppingCartItemId);

    void incrementQuantity(ShoppingCartId shoppingCartId, ShoppingCartItemId id) throws MaxQuantityReachedException;

    void decrementQuantity(ShoppingCartId shoppingCartId, ShoppingCartItemId id) throws MinQuantityReachedException;

    void buy(BuyRequest buyRequest) throws InsufficientQuantityException, FailedToBuyException;
}
