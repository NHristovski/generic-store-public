package hristovski.nikola.shopping_cart.domain.service;

import hristovski.nikola.common.shared.domain.exception.RestRequestException;
import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.model.all.value.Name;
import hristovski.nikola.common.shared.domain.model.all.value.Quantity;
import hristovski.nikola.common.shared.domain.model.inventory.value.ProductReservation;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.shipping.value.Address;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCart;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartId;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartItem;
import hristovski.nikola.common.shared.domain.model.shopping_cart.ShoppingCartItemId;
import hristovski.nikola.common.shared.domain.model.user.ApplicationUserId;
import hristovski.nikola.generic_store.base.domain.DomainEventPublisher;
import hristovski.nikola.generic_store.message.domain.event.command.CreateOrderCommand;
import hristovski.nikola.generic_store.message.domain.event.command.FreeProductsReservationCommand;
import hristovski.nikola.shopping_cart.application.port.exception.MaxQuantityReachedException;
import hristovski.nikola.shopping_cart.application.port.exception.MinQuantityReachedException;
import hristovski.nikola.shopping_cart.domain.model.cart.ShoppingCartEntity;
import hristovski.nikola.shopping_cart.domain.model.cart.ShoppingCartItemEntity;
import hristovski.nikola.shopping_cart.domain.remote.service.InventoryService;
import hristovski.nikola.shopping_cart.domain.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.MINUTES;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ConversionService conversionService;
    private final InventoryService inventoryService;
    private final DomainEventPublisher domainEventPublisher;

    @Override
    public ShoppingCart getShoppingCart(ShoppingCartId shoppingCartId) {
        return conversionService.convert(
                shoppingCartRepository.getOne(shoppingCartId),
                ShoppingCart.class
        );
    }

    @Override
    public ShoppingCart getShoppingCart(ApplicationUserId userId) {
        try {
            ShoppingCartEntity cart = shoppingCartRepository.findByApplicationUserId(userId);

            if (cart == null) {
                return createNewShoppingCart(userId);
            }

            cart.refreshLastUpdate();
            return conversionService.convert(shoppingCartRepository.saveAndFlush(cart), ShoppingCart.class);

        } catch (Exception ex) {
            log.error("Failed to find shopping card for user {}", userId.getId());
            return createNewShoppingCart(userId);
        }
    }

    private ShoppingCart createNewShoppingCart(ApplicationUserId userId) {
        log.info("Creating new shopping cart for user {}", userId.getId());

        ShoppingCartEntity shoppingCartEntity = new ShoppingCartEntity(userId);

        ShoppingCartEntity savedShoppingCartEntity = shoppingCartRepository.save(shoppingCartEntity);
        log.info("Successfully created shopping cart for user {}", userId.getId());

        return conversionService.convert(
                savedShoppingCartEntity,
                ShoppingCart.class
        );
    }

//    @Override
//    public ShoppingCart getShoppingCartItems(ApplicationUserId userId) {
////        ShoppingCart shoppingCart = getShoppingCart(userId);
////
////        Set<ShoppingCartItem> allItems = shoppingCart.getShoppingCartItems();
////
////        shoppingCart.setShoppingCartItems(pendingItems);
////
////        return shoppingCart;
//        log.error(" SHOULD BE IMPLEMENTE");
//    }

    @Override
    public void deleteShoppingCart(ShoppingCartId shoppingCartId) {
        shoppingCartRepository.deleteById(shoppingCartId);
    }

    @Override
    public void addProductToShoppingCart(ProductId productId, ApplicationUserId userId,
                                         Quantity quantity, Money price, Name productName) {

        ShoppingCartEntity cart = shoppingCartRepository.findByApplicationUserId(userId);

        if (cart == null) {
            log.info("The card for user {} is null creating new card", userId.getId());
            cart = shoppingCartRepository.saveAndFlush(new ShoppingCartEntity(userId));
        }

        ShoppingCartItemEntity cartItem = new ShoppingCartItemEntity(productId, price, quantity, productName);

        cart.addShoppingCartItem(cartItem);
        log.info("Added shopping cart item");

        cart.refreshLastUpdate();
        shoppingCartRepository.saveAndFlush(cart);
    }

    @Override
    public Money getShoppingCartPrice(ShoppingCartId shoppingCartId) {
        return findShoppingCartEntity(shoppingCartId)
                .totalPrice();
    }

    @Override
    public ShoppingCart getShoppingCartHistory(ApplicationUserId userId) {
//        ShoppingCart shoppingCart = getShoppingCart(userId);
//
//        Set<ShoppingCartItem> allItems = shoppingCart.getShoppingCartItems();
//
//        Set<ShoppingCartItem> boughtItems = allItems
//                .stream()
//                .filter(item -> item.getStatus().isBought())
//                .collect(Collectors.toSet());
//
//        shoppingCart.setShoppingCartItems(boughtItems);
//
//        return shoppingCart;
        log.error(" SHOULD BE IMPLEMENTED DIFFERENTLY !!!!!!!!!!! ");
        return null;
    }

    @Override
    public ShoppingCartItem getShoppingCardItem(ShoppingCartId shoppingCartId, ShoppingCartItemId shoppingCartItemId) {
        ShoppingCartItemEntity shoppingCartItemEntity =
                findShoppingCartItemEntity(findShoppingCartEntity(shoppingCartId), shoppingCartItemId);

        return conversionService.convert(shoppingCartItemEntity, ShoppingCartItem.class);
    }

//    @Override
//    public ShoppingCartItem addShoppingCardItem(ShoppingCartId shoppingCartId, ShoppingCartItem shoppingCartItem) {
//        return null;
//    }
//
//    @Override
//    public ShoppingCartItem editShoppingCardItem(ShoppingCartId shoppingCartId, ShoppingCartItem shoppingCartItem) {
//        return null;
//    }

    @Override
    public void deleteShoppingCardItem(ShoppingCartId shoppingCartId, ShoppingCartItemId shoppingCartItemId) {

        ShoppingCartEntity shoppingCartEntity = findShoppingCartEntity(shoppingCartId);

        ShoppingCartItemEntity shoppingCartItemEntity =
                findShoppingCartItemEntity(shoppingCartEntity, shoppingCartItemId);

        log.info(" BEFORE DELETE THE SHOPPING CART ITEMS {}", shoppingCartEntity.getShoppingCartItemEntities());

        Optional<ShoppingCartItemEntity> match = shoppingCartEntity.getShoppingCartItemEntities()
                .stream()
                .filter(i -> i.getId().getId().equals(shoppingCartItemId.getId()))
                .findFirst();

        if (match.isPresent()) {

            shoppingCartEntity.getShoppingCartItemEntities()
                    .remove(match.get());

            log.info(" AFTER DELETE THE SHOPPING CART ITEMS {}", shoppingCartEntity.getShoppingCartItemEntities());

            shoppingCartEntity.refreshLastUpdate();

            shoppingCartRepository.saveAndFlush(shoppingCartEntity);

            domainEventPublisher.publish(
                    new FreeProductsReservationCommand(
                            List.of(new ProductReservation(
                                    shoppingCartItemEntity.getProductId(),
                                    shoppingCartItemEntity.getQuantity()
                            ))
                    )
            );
        } else {
            throw new IllegalStateException("Trying to delete shopping cart item that is not in the shopping cart");
        }
    }

    @Override
    public void incrementQuantity(ShoppingCartId shoppingCartId, ShoppingCartItemId shoppingCartItemId)
            throws MaxQuantityReachedException, RestRequestException {

        ShoppingCartEntity shoppingCartEntity = findShoppingCartEntity(shoppingCartId);

//        ShoppingCartItemEntity shoppingCartItemEntity =
//                findShoppingCartItemEntity(shoppingCartId, shoppingCartItemId);
//        shoppingCartEntity.getShoppingCartItemEntities()
        ShoppingCartItemEntity shoppingCartItemEntity =
                findShoppingCartItemEntity(shoppingCartEntity, shoppingCartItemId);

        boolean success = inventoryService.decrementStock(shoppingCartItemEntity.getProductId());

        if (!success) {
            throw new MaxQuantityReachedException("Max quantity reached!");
        }

//        Quantity incrementedQuantity = shoppingCartItemEntity.getQuantity().plus(1L);

        shoppingCartItemEntity.incrementQuantity(1L);

//        Set<ShoppingCartItemEntity> newItems = shoppingCartEntity.getShoppingCartItemEntities()
//                .stream()
//                .map(i -> {
//                    if (i.getId().getId().equals(shoppingCartItemId.getId())) {
//                        i.incrementQuantity(1L);
//                        return i;
//                    }
//                    return i;
//                })
//                .collect(Collectors.toSet());
//
//        shoppingCartEntity.setShoppingCartItemEntities(newItems);
        shoppingCartEntity.refreshLastUpdate();
        log.info("The quantity of the shopping cart entity now is: {}", shoppingCartItemEntity.getQuantity());

        shoppingCartRepository.saveAndFlush(shoppingCartEntity);
    }

    private ShoppingCartItemEntity findShoppingCartItemEntity(ShoppingCartEntity shoppingCartEntity, ShoppingCartItemId shoppingCartItemId) {
        return shoppingCartEntity.getShoppingCartItemEntities().stream()
                .filter(cartItem -> cartItem.getId().getId().equals(shoppingCartItemId.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(
                        "Failed to find shopping cart item with id " + shoppingCartItemId.getId()
                                + " from shopping cart with id " + shoppingCartEntity.getId().getId()
                ));
    }

    private ShoppingCartEntity findShoppingCartEntity(ShoppingCartId shoppingCartId) {
        return shoppingCartRepository.findById(shoppingCartId)
                .orElseThrow(() -> new RuntimeException(
                                "Failed to find shopping cart with id " + shoppingCartId.getId()
                        )
                );
    }

    @Override
    public void decrementQuantity(ShoppingCartId shoppingCartId, ShoppingCartItemId shoppingCartItemId)
            throws MinQuantityReachedException {
        ShoppingCartEntity shoppingCartEntity = findShoppingCartEntity(shoppingCartId);

        ShoppingCartItemEntity shoppingCartItemEntity =
                findShoppingCartItemEntity(shoppingCartEntity, shoppingCartItemId);

        if (shoppingCartItemEntity.getQuantity().getQuantity().equals(1L)) {
            throw new MinQuantityReachedException("The shopping cart item has only 1 product");
        }

        Quantity decrementedQuantity = shoppingCartItemEntity.getQuantity().minus(1L);

        shoppingCartItemEntity.setQuantity(decrementedQuantity);
        shoppingCartEntity.refreshLastUpdate();

        shoppingCartRepository.save(shoppingCartEntity);

        domainEventPublisher.publish(
                new FreeProductsReservationCommand(
                        List.of(new ProductReservation(
                                shoppingCartItemEntity.getProductId(),
                                new Quantity(1L)
                        ))
                )
        );
    }


    @Override
    public void buy(ShoppingCartId shoppingCartId, Address shippingAddress) {
        ShoppingCartEntity shoppingCartEntity = findShoppingCartEntity(shoppingCartId);

        Set<ShoppingCartItemEntity> shoppingCartItemEntities = shoppingCartEntity.getShoppingCartItemEntities();

        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemEntities.stream()
                .map(item -> conversionService.convert(item, ShoppingCartItem.class))
                .collect(Collectors.toList());

        log.info("The shopping cart items are: {}", shoppingCartItems);

        domainEventPublisher.publish(new CreateOrderCommand(shoppingCartEntity.getApplicationUserId(),
                shoppingCartItems,
                shoppingCartEntity.totalPrice(),
                shippingAddress
        ));

        shoppingCartEntity.clear();

        shoppingCartRepository.saveAndFlush(shoppingCartEntity);
    }

    @Override
    public void clearUnusedShoppingCarts() {
        List<ShoppingCartEntity> carts = this.shoppingCartRepository.findAll()
                .stream()
                .filter(shoppingCartEntity -> shoppingCartEntity.getLastUpdateTimestamp().isBefore(
                        Instant.now().minus(30, MINUTES))
                ).collect(Collectors.toList());

        log.info("Unused shopping carts {}", carts);

        carts.forEach(cart -> {
                    domainEventPublisher.publish(new FreeProductsReservationCommand(cart.clearSaleItems()));
                    shoppingCartRepository.saveAndFlush(cart);
                }
        );
    }

//    private final ShoppingCartRepository shoppingCartRepository;
//    private final ProductService productService;
//    private final ShoppingCartItemService shoppingCartItemService;
//    private final PaymentService paymentService;
//
//
//
//    @Override
//    public ShoppingCart addShoppingCart(ShoppingCart shoppingCart) {
//        return shoppingCartRepository.save(shoppingCart);
//    }
//
//    @Override
//    public void editShoppingCart(ShoppingCart shoppingCart) {
//
//    }
//
//    @Override
//    public void deleteShoppingCart(Long shoppingCartId) {
//
//    }
//
//    @Override
//    public void addProductToShoppingCart(Long productId, Integer quantity, String username) throws ProductNotFoundException {
//
//
//        ShoppingCart shoppingCart = getShoppingCart(username);
//
//        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
//        shoppingCartItem.setQuantity(quantity);
//        shoppingCartItem.setProduct(productService.getById(productId));
//        shoppingCartItem.setPending(true);
//        shoppingCartItem.setBought(false);
//        shoppingCartItem.setDateBought(null);
//
//        List<ShoppingCartItem> shoppingCartItems = shoppingCart.getShoppingCartItems();
//        if (shoppingCartItems == null){
//            shoppingCart.setShoppingCartItems(new ArrayList<>());
//        }
//
//        for (var item : shoppingCart.getShoppingCartItems()){
//            if (item.getProduct().getId().equals(productId) && item.isPending()){
//                item.setQuantity(item.getQuantity() + quantity);
//                shoppingCartRepository.save(shoppingCart);
//                return;
//            }
//        }
//
//        ShoppingCartItem item = shoppingCartItemService.addShoppingCardItem(shoppingCartItem);
//
//        shoppingCart.getShoppingCartItems().add(item);
//
//        shoppingCartRepository.save(shoppingCart);
//    }
//
//    @Override
//    public Double getShoppingCartPrice(Long shoppingCartId) {
//        return null;
//    }
//
//    @Override
//    public ShoppingCart getShoppingCartHistory(String username) {
//        ShoppingCart shoppingCart = getShoppingCart(username);
//
//        List<ShoppingCartItem> allItems = shoppingCart.getShoppingCartItems();
//
//        List<ShoppingCartItem> historyItems = allItems.stream().filter(ShoppingCartItem::isBought)
//                .collect(Collectors.toList());
//
//        Collections.reverse(historyItems);
//
//        ShoppingCart shoppingCartResult = new ShoppingCart();
//        shoppingCartResult.setUsername(shoppingCart.getUsername());
//        shoppingCartResult.setId(shoppingCart.getId());
//        shoppingCartResult.setShoppingCartItems(historyItems);
//
//        return shoppingCartResult;
//    }
//
//    @Override
//    public void deleteItem(Long shoppingCartId, Long itemId) {
//        ShoppingCart shoppingCart = getShoppingCart(shoppingCartId);
//
//        int idx = -1;
//        List<ShoppingCartItem> items = shoppingCart.getShoppingCartItems();
//
//        for (int i = 0; i < items.size(); i++){
//            if (items.get(i).getId() == itemId){
//                idx = i;
//                break;
//            }
//        }
//
//        if (idx != -1) {
//            items.remove(idx);
//        }
//        log.info("Removed item on idx {}, items now {}",idx,items);
//        shoppingCartRepository.save(shoppingCart);
//
//        //shoppingCartItemService.deleteShoppingCardItem(itemId);
//    }
//
//    @Override
//    public void buy(BuyRequest buyRequest) throws InvalidQuantityException, FailedToBuyException, ProductNotFoundException {
//        ShoppingCart shoppingCart = getShoppingCart(buyRequest.getShoppingCartId());
//
//        if (!validShoppingCart(shoppingCart)){
//            throw new InvalidQuantityException();
//        }
//
//        boolean success = paymentService.payment(shoppingCart.totalPrice(),
//                buyRequest.getCardHolderName(),
//                buyRequest.getCreditCard(),
//                buyRequest.getExpiryDate(),
//                buyRequest.getCcv());
//
//        if (success){
//            buyHelper(shoppingCart);
//        }else{
//            throw new FailedToBuyException();
//        }
//
//    }
//
//    private void buyHelper(ShoppingCart shoppingCart) throws ProductNotFoundException {
//        for (var item: shoppingCart.getShoppingCartItems()) {
//            if (item.isPending()) {
//                item.setBought(true);
//                item.setDateBought(Instant.now());
//                item.setPending(false);
//
//                Product product = item.getProduct();
//                product.setStock(product.getStock() - item.getQuantity());
//
//                shoppingCartItemService.editShoppingCardItem(item);
//
//                AddProductRequest request = new AddProductRequest(product.getTitle(),
//                        product.getImageLocation(),
//                        product.getDescription(),
//                        product.getPrice(),
//                        product.getStock());
//
//                if (product.getCategories() != null) {
//                    request.setCategoryNames(product.getCategories().stream().map(Category::getCategoryName)
//                    .collect(Collectors.toList()));
//                }else{
//                    request.setCategoryNames(new ArrayList<>());
//                }
//
//                productService.editProduct(product.getId(), request);
//            }
//        }
//    }
//
//    private boolean validShoppingCart(ShoppingCart shoppingCart) throws ProductNotFoundException {
//        for (var item : shoppingCart.getShoppingCartItems()) {
//            if (item.isPending()) {
//
//                Product byId = productService.getById(item.getProduct().getId());
//                if (item.getQuantity() > byId.getStock()) {
//                    log.error("the item {} has quantity {} but product has stock {}"
//                            , item.getId(), item.getQuantity(), byId.getStock());
//                    return false;
//                }
//            }
//        }
//
//        return true;
//    }
}
